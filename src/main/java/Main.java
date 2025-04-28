import model.items.plants.Tree;
import view.AppView;

public class Main {
    public static void main(String[] args) {
      //  (new AppView()).run();
        Tree tree = Tree.trees.get("banana");
        System.out.println(tree.getName());
    }
}
