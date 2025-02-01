package com.techbisatest.blogapi;
import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.techbisatest.blogapi.models.*;
import com.techbisatest.blogapi.services.AuthorService0;
import com.techbisatest.blogapi.services.BlogService;
import com.techbisatest.blogapi.utils.Errors;

import java.util.List;

public class BlogAPI {
    private static BlogService blogService = new BlogService();
    private static AuthorService0 authorService = new AuthorService0();
    private static Errors error = new Errors();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        port(4567);

        // Crear un blog
        post("/blogs", (req, res) -> {
            Blog blog = gson.fromJson(req.body(), Blog.class);

            // Verificar si el autor existe o si debe ser creado
            Author author = authorService.getOrCreateAuthor(blog.getAuthor());

            // Asociamos el autor con el blog
            blog.setAuthor(author);  // Asegúrate de que el blog tenga el autor correcto

            // Validar si el autor tiene blogs (si es necesario)
            String validationMessage = authorService.validateAuthorForBlog(blog);
            if (!validationMessage.isEmpty()) {
                res.status(400);  // Bad Request
                return gson.toJson(validationMessage);
            }

            String mensaje = error.addBlogError(blog);
            if (!mensaje.isEmpty()) {
                res.status(400);
                return gson.toJson(mensaje);
            }

            blogService.addBlog(blog);
            res.status(201);
            return gson.toJson("Blog creado correctamente");
        });

        // Obtener todos los blogs
        get("/blogs", (req, res) -> {
            res.type("application/json");
            return gson.toJson(blogService.getAllBlogs());
        });

        // Obtener un blog por ID
        get("/blogs/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Blog blog = blogService.findBlogById(id);
            if (blog == null) {
                res.status(404);
                return gson.toJson("Blog no encontrado");
            }
            res.type("application/json");
            return gson.toJson(blog);
        });

        // Agregar comentario a un blog
        post("/blogs/:id/comments", (req, res) -> {
            // Obtener el blog por ID
            int blogId = Integer.parseInt(req.params(":id"));
            Blog blog = blogService.findBlogById(blogId);

            // Verificar si el blog existe
            if (blog == null) {
                res.status(404);  // Not Found
                return gson.toJson("Blog no encontrado");
            }

            // Verificar si los comentarios están permitidos en el blog
            if (!blog.isAllowsComments()) {
                res.status(403);  // Forbidden
                return gson.toJson("Comentarios no permitidos en este blog");
            }

            // Si los comentarios están permitidos, agregar el comentario
            Comment comment = gson.fromJson(req.body(), Comment.class);
            blog.addComment(comment);  // Método para agregar el comentario al blog

            res.status(201);  // Created
            return gson.toJson("Comentario agregado correctamente");
        });

        put("/blogs/:id", (req, res) -> {
            int blogId = Integer.parseInt(req.params(":id"));
            Blog blog = blogService.findBlogById(blogId);

            if (blog == null) {
                res.status(404);
                return gson.toJson("Blog no encontrado");
            }

            JsonObject requestBody = JsonParser.parseString(req.body()).getAsJsonObject();
            String newTitle = requestBody.get("title").getAsString();
            String newContent = requestBody.get("content").getAsString();
            boolean allowsComments = requestBody.get("allowsComments").getAsBoolean();

            blogService.updateBlog(blogId, newTitle, newContent, allowsComments);
            res.status(200);
            return gson.toJson("Blog actualizado correctamente");
        });

        get("/blogs/:id/history", (req, res) -> {
            int blogId = Integer.parseInt(req.params(":id"));
            List<BlogHistory> blogHistory = blogService.getBlogHistory(blogId);

            if (blogHistory.isEmpty()) {
                res.status(404);
                return gson.toJson("No hay historial para este blog");
            }

            res.status(200);
            return gson.toJson(blogHistory);
        });


        put("/blogs/:id/allows-comments", (req, res) -> {
            int blogId = Integer.parseInt(req.params(":id"));
            Blog blog = blogService.findBlogById(blogId);

            if (blog == null) {
                res.status(404);
                return gson.toJson("Blog no encontrado");
            }

            JsonObject requestBody = JsonParser.parseString(req.body()).getAsJsonObject();
            boolean allowsComments = requestBody.get("allowsComments").getAsBoolean();

            if (blog.isAllowsComments() != allowsComments) {
                blogService.addHistory(blogId, "allowsComments",
                        String.valueOf(blog.isAllowsComments()),
                        String.valueOf(allowsComments));

                blog.setAllowsComments(allowsComments);
                res.status(200);
                return gson.toJson("Disponibilidad de comentarios actualizada y registrada en el historial");
            } else {
                res.status(200);
                return gson.toJson("No hubo cambios en la disponibilidad de comentarios");
            }
        });



        // Obtener comentarios de un blog
        get("/blogs/:id/comments", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            res.type("application/json");
            return gson.toJson(blogService.getCommentsByBlogId(id));
        });

        // Manejo de errores globales (para cualquier excepción inesperada)
        exception(Exception.class, (ex, req, res) -> {
            res.status(500); // Error interno del servidor
            res.type("application/json");
            res.body("{\"error\":\"" + ex.getMessage() + "\"}");
        });

        notFound((req, res) -> {
            res.type("application/json");
            return "{\"error\":\"Ruta no encontrada\"}";
        });


    }
}
