package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.*;
import tikape.runko.domain.*;
import java.util.List;
import java.util.ArrayList;

public class Main {

    //test
    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:pirtelot.db");
        database.init();

        PirteloDao pirteloDao = new PirteloDao(database);
        AinesDao ainesDao = new AinesDao(database);
        
        List<Pirtelo> pirtelolista = new ArrayList<>();
        
        for (Pirtelo pirtelo: pirteloDao.findAll()) {
            pirtelolista.add(pirtelo);
        }
        
        for (Pirtelo pirtelo: pirteloDao.findAll()) {
            System.out.println(pirtelo.getNimi() +" "+ pirtelo.getId());
        }

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
            String pirtelo = req.queryParams("pirtelö");
            pirteloDao.saveOrUpdate(new Pirtelo(pirtelo));
            res.redirect("/pirtelot");
            return "";
        });

        Spark.get("/raakaaineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("??", pirteloDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "raaka-aineet");
        }, new ThymeleafTemplateEngine());
    }
}
