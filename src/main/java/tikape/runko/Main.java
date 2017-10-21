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
        AinesPirteloDao ainesPirteloDao = new AinesPirteloDao(database);

        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/pirtelot", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("pirtelot", pirteloDao.findAll());
            map.put("ainekset", ainesDao.findAll());

            return new ModelAndView(map, "pirtelot");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/pirtelot", (req, res) -> {
            String pirtelo = req.queryParams("pirtelo");
            pirteloDao.saveOrUpdate(new Pirtelo(pirtelo));
            res.redirect("/pirtelot");
            return "";
        });
        
//        Spark.post("/pirtelot/:id/aines", (req, res) -> {
//            int pirteloId = Integer.parseInt(req.params("id"));
//            int ainesId = Integer.parseInt(req.queryParams("ainesid"));
//            
//            System.out.println(pirteloDao.findOne(pirteloId));
//            System.out.println(ainesDao.findOne(ainesId));
//            
//            Pirtelo p = pirteloDao.findOne(pirteloId);
//            Aines a = ainesDao.findOne(ainesId);
//            
//            p.getAinekset().add(a);
//            a.getPirtelot().add(p);
//            
//            pirteloDao.saveOrUpdate(p);
//            //tuplaa liitostaulun, ei vielä valmis
//            //ainesDao.saveOrUpdate(a);
//            
//            res.redirect("/pirtelot/" + pirteloId);
//            return "";
//        });
        
        Spark.get("/pirtelot/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Integer pirteloId = Integer.parseInt(req.params("id"));
            Pirtelo pirtelo = pirteloDao.findOne(Integer.parseInt(req.params("id")));
            map.put("pirtelo", pirtelo);
            map.put("raakaaineet", pirtelo.getAinekset());
            map.put("ainekset", ainesDao.findAll());
            map.put("pirteloaineet", ainesPirteloDao.findAllForPirtelo(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "pirtelo");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/pirtelot/:id/aines", (req, res) -> {
            Integer pirteloId = Integer.parseInt(req.params("id"));
            Pirtelo p = pirteloDao.findOne(pirteloId);
            Aines a = ainesDao.findOne(Integer.parseInt(req.queryParams("ainesid")));
            Double maara = Double.parseDouble(req.queryParams("maara"));
            String tyyppi = req.queryParams("tyyppi");  
            AinesPirtelo ap = new AinesPirtelo(p, a, maara, tyyppi);
            ainesPirteloDao.saveOrUpdate(ap);

            res.redirect("/pirtelot/" + pirteloId);
            return "";
        });
        
        Spark.post("/pirtelot/:id/aines", (req, res) -> {
            Integer pirteloId = Integer.parseInt(req.params("id"));
            Pirtelo p = pirteloDao.findOne(pirteloId);
            Aines a = ainesDao.findOne(Integer.parseInt(req.queryParams("ainesid")));
            Double maara = Double.parseDouble(req.queryParams("maara"));
            String tyyppi = req.queryParams("tyyppi");  
            AinesPirtelo ap = new AinesPirtelo(p, a, maara, tyyppi);
            ainesPirteloDao.saveOrUpdate(ap);

            res.redirect("/pirtelot/" + pirteloId);
            return "";
        });
        
        Spark.post("/pirtelot/:id/ohje", (req, res) -> {
            Integer pirteloId = Integer.parseInt(req.params("id"));
            Pirtelo p = pirteloDao.addOhje(pirteloId, req.queryParams("ohje"));
            res.redirect("/pirtelot/" + pirteloId);
            return "";
        });
        
        Spark.post("/pirtelot/:pid/:aid", (req, res) -> {
            Integer pid = Integer.parseInt(req.params("pid"));
            Integer aid = Integer.parseInt(req.params("aid"));
            ainesPirteloDao.deleteOne(pid, aid);
            res.redirect("/pirtelot/" + pid);
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
