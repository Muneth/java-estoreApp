package fr.cda.projet;
import java.util.*;
import fr.cda.util.*;

/**
 * The type Site.
 */
public class Site {
    private final ArrayList<Product> stock;
    private final ArrayList<Order> orders;
    private final ArrayList<Order> orderNotDelivered;
    private final ArrayList<Order> ordersDelivered;

    /**
     * Instantiates a new Site.
     */
    public Site() {
        stock = new ArrayList<>();
        orders = new ArrayList<>();
        orderNotDelivered = new ArrayList<>();
        ordersDelivered = new ArrayList<>();

        initialiserStock();
        initialiserCommande();
        managingStock();
    }

    /**
     * Display all products string.
     *
     * @return the string
     */
    public String displayAllProducts() {
        StringBuilder res= new StringBuilder();
        for (Product prod : stock) {
            res.append(prod.toString()).append("\n");
        }
        return res.toString();
    }

    /**
     * Display all orders string.
     *
     * @return the string
     */
    public String displayAllOrders() {
        StringBuilder res= new StringBuilder();
        for (Order cmd : orders) {
            res.append(cmd.toString(false)).append("\n");
        }
        return res.toString();
    }

    /**
     * Display order string.
     *
     * @param number the number
     * @return the string
     */
    public String displayOrder(int number){
        StringBuilder res= new StringBuilder();
        boolean found = false;
        for (Order order : orders) {
            int cmdNum = order.getCmdNumber();
            if (cmdNum == number) {
                res.append(order.toString(false));
                found = true;
            }
        }
        if(!found) {
            res = new StringBuilder("   the order does not exists   ");
        }
        return res.toString();
    }

    /**
     * Display orders delivered string.
     *
     * @return the string
     */
    public String displayOrdersDelivered(){
        StringBuilder res= new StringBuilder("""
                Les commmandes livres  :
                =============================
                                
                """);
        for (Order cmd : ordersDelivered) {
            res.append(cmd.toString(false)).append("\n");
        }
        return res.toString();
    }

    /**
     * Display orders not delivered string.
     *
     * @return the string
     */
    public String displayOrdersNotDelivered(){
        StringBuilder res= new StringBuilder("""
                Les commmandes non livres  :
                =============================
                                
                """);
        for (Order cmd : orderNotDelivered) {
            res.append(cmd.toString(true)).append("\n");
        }
        return res.toString();
    }

    /**
     * Gets order by number.
     *
     * @param num the num
     * @return the order by number
     */
    public Order getOrderByNumber(int num)
    {
        for (Order order : orders)
            if (order.getCmdNumber() == num)
                return order;
        return (null);
    }

    /**
     * Calculate sales string.
     *
     * @return the string
     */
    public String calculateSales(){
        String res ="";
        StringBuilder res1 = new StringBuilder();
        String cmdRef;
        int cmdQuantity;
        double total = 0;
        for (Product product : stock) {
            String stockRef = product.reference();
            for (Order order : ordersDelivered) {
                res = "Order  :    " + order.getCmdNumber() + "  " + '\n';
                String[] refs = order.getReferences().toArray(new String[0]);
                for (String ref : refs) {
                    String[] refsChamps = ref.split("=");
                    cmdRef = refsChamps[0];
                    cmdQuantity = Integer.parseInt(refsChamps[1]);
                    if (stockRef.equals(cmdRef)) {
                        res1.append('\n').append("            ").append(product.name()).append("    ").append(cmdQuantity).append("    ").append(cmdQuantity * product.price()).append('\n');
                        total = total + (cmdQuantity * product.price());
                    }
                }
                res = res + res1 + '\n' + "=====================" + '\n' + " SOMME   :   " + total + "   euros";
            }
        }
        return res;
    }

    private void initialiserStock() {
        String[] lignes = Terminal.lireFichierTexte("data/Produits.txt");
        assert lignes != null;
        for(String ligne :lignes) {
            String[] champs = ligne.split(";",4);
            String reference = champs[0];
            String nom = champs[1];
            double prix = Double.parseDouble(champs[2]);
            int quantity =  Integer.parseInt(champs[3]);
            Product p = new Product(reference, nom, prix, quantity);
            stock.add(p);
        }
    }

    private void initialiserCommande() {
        String[] lignes = Terminal.lireFichierTexte("data/Commandes.txt");
        assert lignes != null;
        for(String ligne :lignes) {
            String[] champs = ligne.split(";",4);
            int cmdNum = Integer.parseInt(champs[0]);
            String date = champs[1];
            String nom = champs[2];
            String reference = champs[3];
            Order c = getOrderByNumber(cmdNum);
            if (c == null){
                c = new Order(cmdNum, date, nom);
                c.addRef(reference);

                orders.add(c);
            } else {
                c.addRef(reference);
            }

        }
    }

    private void managingStock(){
        String cmdRef = "";
        int cmdQuantity = 0;
        String res;
        for (Order order : orders) {
            String[] refs = order.getReferences().toArray(new String[0]);
            for (String ref : refs) {
                String[] refsChamps = ref.split("=");
                cmdRef = refsChamps[0];
                cmdQuantity = Integer.parseInt(refsChamps[1]);
                if(notHaveEnoughQuantity(cmdRef, cmdQuantity)){
                    res = "   il manque    " + (cmdQuantity - currentQuantityInStock(cmdRef, cmdQuantity)) +"    "+ cmdRef;
                    order.addReasons(res);
                }
            }
            if(notHaveEnoughQuantity(cmdRef, cmdQuantity)){
                orderNotDelivered.add(order);
            }
            if(haveEnoughQuantity(cmdRef, cmdQuantity)){
                ordersDelivered.add(order);
            }
        }
    }

    private int currentQuantityInStock(String ref, int quan){
        int stkquan = 0;
        for(Product p : stock){
            String key = p.reference();
            int val = p.quantity();
            if (key.equals(ref) && val < quan) {
                stkquan = val;
            }
        }
        return stkquan;
    }

    private boolean haveEnoughQuantity(String ref, int quan){
        boolean stockRef = false;
        for(Product p : stock){
            String key = p.reference();
            int val = p.quantity();
            if (key.equals(ref) && val > quan) {
                stockRef = true;
                break;
            }
        }
        return stockRef;
    }

    private boolean notHaveEnoughQuantity(String ref, int quan){
        boolean lessQuan = false;
        for(Product p : stock){
            String key = p.reference();
            int val = p.quantity();
            if (key.equals(ref) && val < quan) {
                lessQuan = true;
                break;
            }
        }
        return lessQuan;
    }
}