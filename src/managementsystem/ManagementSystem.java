package managementsystem;

import controller.CategoryDAO;
import controller.SupplierDAO;
import controller.ArticleDAO;
import controller.MvtDAO;
import model.Category;
import model.Supplier;
import model.Article;
import model.Mvt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

public class ManagementSystem {

    public static void main(String[] args) {
        
        CategoryDAO categoryDAO = new CategoryDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        ArticleDAO articleDAO = new ArticleDAO();
        MvtDAO mvtDAO = new MvtDAO();

        String fakeChamp = UUID.randomUUID().toString().substring(0, 4);
        System.out.println("=== TEST CRUD " + fakeChamp + " ===\n");

        System.out.println("--- CREATE ---");
        Category catParent = new Category("Materiel_" + fakeChamp, "Tout le materiel");
        Category catEnfant = new Category("Informatique_" + fakeChamp, "PC et accessoires");
        catParent.addSubCategory(catEnfant);
        categoryDAO.insert(catParent);
        categoryDAO.insert(catEnfant);

        Supplier fournisseur = new Supplier("Fournisseur_" + fakeChamp, "test_" + fakeChamp + "@mail.com", "0123", "VAT1");
        supplierDAO.insert(fournisseur);

        Article article = new Article("Souris_" + fakeChamp, "Souris sans fil", 50.0, 15.0, 25.0, catEnfant);
        articleDAO.insert(article);

        Mvt mvtEntree = new Mvt(
                null, 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
                "Entrée initiale", 
                Mvt.TypeMvt.INPUT, 
                50.0, 
                article.getId(), 
                fournisseur.getId()
        );
        mvtDAO.insert(mvtEntree);

        Mvt mvtSortie = new Mvt(
                null, 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 
                "Sortie test", 
                Mvt.TypeMvt.OUTPUT, 
                5.0, 
                article.getId(), 
                fournisseur.getId()
        );
        mvtDAO.insert(mvtSortie);

        System.out.println("\n--- READ (findById) ---");
        Category catRecherchee = categoryDAO.findById(catEnfant.getId().toString());
        System.out.println("Catégorie : " + catRecherchee);
        
        Article articleRecherche = articleDAO.findById(article.getId().toString());
        System.out.println("Article : " + articleRecherche);

        System.out.println("\n--- UPDATE ---");
        articleRecherche.setQty(100.0);
        articleRecherche.setPrixVente(30.0);
        articleDAO.update(articleRecherche);
        System.out.println("Article modifié : " + articleDAO.findById(article.getId().toString()));

        Supplier fournisseurModifie = supplierDAO.findById(fournisseur.getId().toString());
        fournisseurModifie.setTel("0999999999");
        supplierDAO.update(fournisseurModifie);
        System.out.println("Fournisseur modifié : " + supplierDAO.findById(fournisseur.getId().toString()));

        System.out.println("\n--- SEARCH BY ARTICLE ---");
        ArrayList<Mvt> mvtsParArticle = mvtDAO.findByArticle(article.getId().toString());
        for (Mvt m : mvtsParArticle) {
            System.out.println("Mouvement : " + m);
        }

        System.out.println("\n--- SEARCH BY SUPPLIER ---");
        ArrayList<Mvt> mvtsParFournisseur = mvtDAO.findBySupplier(fournisseur.getId().toString());
        for (Mvt m : mvtsParFournisseur) {
            System.out.println("Mouvement : " + m);
        }

        System.out.println("\n--- SEARCH BY TYPE ---");
        ArrayList<Mvt> mvtsEntree = mvtDAO.findByType(Mvt.TypeMvt.INPUT);
        System.out.println("Nombre d'entrées trouvées : " + mvtsEntree.size());
        
        ArrayList<Mvt> mvtsSortie = mvtDAO.findByType(Mvt.TypeMvt.OUTPUT);
        System.out.println("Nombre de sorties trouvées : " + mvtsSortie.size());

        System.out.println("\n--- DELETE ---");
        mvtDAO.delete(mvtEntree.getId().toString());
        mvtDAO.delete(mvtSortie.getId().toString());
        articleDAO.delete(article.getId().toString());
        categoryDAO.delete(catEnfant.getId().toString());
        categoryDAO.delete(catParent.getId().toString());
        System.out.println("Suppressions effectuées.");

        System.out.println("\n--- SELECT ALL ---");
        ArrayList<Article> articlesRestants = articleDAO.select();
        System.out.println("Nombre d'articles restants : " + articlesRestants.size());

        System.out.println("\n=== FIN ===");
    }
}