import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private ArrayList<Item> items;

    public ShoppingList() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void markItemAsBought(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).markAsBought();
        }
    }

    public void showAllItems() {
        if (items.isEmpty()) {
            System.out.println("Daftar belanja kosong.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }

    public void editItem(int index, String newName, int newQuantity) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            item.setName(newName);
            item.setQuantity(newQuantity);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> searchItems(String keyword) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }
}

// copyrightby informatika