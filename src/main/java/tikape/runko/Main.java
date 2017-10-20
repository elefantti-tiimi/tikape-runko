package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.*;
import tikape.runko.domain.*;
//import java.util.List;
//import java.util.ArrayList;

public class Main {

    //test
    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:pirtelot.db");
        database.init();

        PirteloDao pirteloDao = new PirteloDao(database);
        AinesDao ainesDao = new AinesDao(database);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/pirtelot", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("pirtelot", pirteloDao.findAll());

            return new ModelAndView(map, "pirtelot");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/pirtelot", (req, res) -> {
            String pirtelo = req.queryParams("pirtelo");
            pirteloDao.saveOrUpdate(new Pirtelo(pirtelo));
            res.redirect("/pirtelot");
            return "";
        });
        
        Spark.get("/pirtelot/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Pirtelo pirtelo = pirteloDao.findOne(Integer.parseInt(req.params("id")));
            map.put("pirtelo", pirtelo);
            map.put("raakaaineet", pirtelo.getAinekset());

            return new ModelAndView(map, "pirtelo");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/pirtelot/:id", (req, res) -> {
            pirteloDao.delete(Integer.parseInt(req.params("id")));
            res.redirect("/pirtelot");
            return "";
        });

        Spark.get("/raakaaineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aineet", ainesDao.findAll());

            return new ModelAndView(map, "raakaaineet");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/raakaaineet", (req, res) -> {
            String aines = req.queryParams("aines");
            ainesDao.saveOrUpdate(new Aines(aines));
            res.redirect("/raakaaineet");
            return "";
        });
        
        Spark.get("/raakaaineet/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Aines aines = ainesDao.findOne(Integer.parseInt(req.params("id")));
            map.put("aines", aines);

            return new ModelAndView(map, "aines");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/raakaaineet/:id", (req, res) -> {
            ainesDao.delete(Integer.parseInt(req.params("id")));
            res.redirect("/raakaaineet");
            return "";
        });
    }
}
