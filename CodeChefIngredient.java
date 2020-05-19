import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CodeChefIngredient {

    enum Category {
        FAT,
        FIBER,
        CARB
    }

    public static void main(String args[]) {
        // Scanner in = new Scanner(System.in);
        
        int days = 5;

        int count = 3;

        List<Ingredint> ingredients = new ArrayList<>();

        int k = 0;
        for (String i : "FATOil FIBERSpinach CARBRice FATCheese FIBERBeans".split(" ")) {
            ingredients.add(new Ingredint(i, k++));
        }

        findIngredient(ingredients, days, count);
    }

    static void findIngredient(List<Ingredint> ingredients, int days, int ingredientsCount) {

        List<Ingredint> fats = new ArrayList<>();
        List<Ingredint> fibers = new ArrayList<>();
        List<Ingredint> carbs = new ArrayList<>();

        int minInGrp = (int) Math.ceil(ingredientsCount * 0.6);

        for (int i = 0; i < days; i++) {

            Ingredint ingredint = ingredients.get(i);
            
            if(ingredint.category.equals(Category.FAT)) {
                fats.add(ingredint);
            }
            if(ingredint.category.equals(Category.CARB)) {
                carbs.add(ingredint);
            }
            if(ingredint.category.equals(Category.FIBER)) {
                fibers.add(ingredint);
            }

            if((fats.size() >= minInGrp || carbs.size() >= minInGrp || fibers.size() >= minInGrp)
                && (fats.size() + fibers.size() + carbs.size() >= ingredientsCount)) {

                    //cook
                    List<Ingredint> ingredints = pickIngredints(fats, fibers, carbs, minInGrp, ingredientsCount);
                    
                    for (Ingredint ing : ingredints) {
                        System.out.print(ing.id);
                    }

            } else {
                //do not cook
                System.out.print("-");
            }

        }
    }

    static List<Ingredint> pickIngredints(List<Ingredint> fats, List<Ingredint> fibers, List<Ingredint> carbs, int minInGrp, int count ) {

        List<Ingredint> res = new ArrayList<>();

        List<Ingredint> ingredints = new ArrayList<>();
        ingredints.addAll(fats);
        ingredints.addAll(fibers);
        ingredints.addAll(carbs);

        ingredints.sort(Comparator.comparing(i -> i.rank));

        if(fats.size() >= minInGrp) {
            addIngredient(Category.FAT, minInGrp, count, ingredints, res);
        }

        if(fibers.size() >= minInGrp) {
            addIngredient(Category.FIBER, minInGrp, count, ingredints, res);
        }

        if(carbs.size() >= minInGrp) {
            addIngredient(Category.CARB, minInGrp, count, ingredints, res);
        }

        //remove these
        for(Ingredint i : res) {
            if(fats.contains(i)) {
                fats.remove(i);
            }
            if(carbs.contains(i)) {
                carbs.remove(i);
            }
            if(fibers.contains(i)) {
                fibers.remove(i);
            }
        }

        return res;
    }

    static void addIngredient(Category category, int minInGrp, int count, List<Ingredint> ingredints, List<Ingredint> res) {
        int i = 0;
        int freeToTake = count - minInGrp;
        for (Ingredint ingredint : ingredints) {
            if(count-- > 0) {
                if(i < freeToTake--) {
                    if(ingredint.category == category) {
                        freeToTake--;
                        res.add(ingredint);
                    } else {
                        res.add(ingredint);
                    }
                } else {
                    if(ingredint.category == category) {
                        res.add(ingredint);
                    }
                }
            }
        }
    }

    static class Ingredint {

        public int rank;
        public String id;
        public Category category;
    
        Ingredint(String id, int rank) {
            this.id = id;
            this.rank = rank;
            if(id.startsWith("FAT")) {
                this.category = Category.FAT;
            }
            if(id.startsWith("FIBER")) {
                this.category = Category.FIBER;
            }
            if(id.startsWith("CARB")) {
                this.category = Category.CARB;
            }
        }
    }
}