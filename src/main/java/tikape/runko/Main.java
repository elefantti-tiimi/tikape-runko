package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.*;
import tikape.runko.domain.*;

public class Main {

    //test
    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:pirtelot.db");
        database.init();

        PirteloDao pirteloDao = new PirteloDao(database);
        
        for (Pirtelo pirtelo: pirteloDao.findAll()) {
            System.out.println(pirtelo.getNimi() +" "+ pirtelo.getId());
        }
        
        pirteloDao.delete(2);
        pirteloDao.saveOrUpdate(new Pirtelo("Peruspirtelö"));
        
        for (Pirtelo pirtelo: pirteloDao.findAll()) {
            System.out.println(pirtelo.getNimi() +" "+ pirtelo.getId());
        }

        Spark.get("*", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/pirtelot", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("pirtelo", pirteloDao.findAll());

            return new ModelAndView(map, "pirtelot");
        }, new ThymeleafTemplateEngine());

        Spark.get("/raakaaineet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("??", pirteloDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());
    }
}
