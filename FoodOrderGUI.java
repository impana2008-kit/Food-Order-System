import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

// Order class
class Order {
    int orderId;
    String customerName;
    String foodItem;

    Order(int id, String name, String item) {
        this.orderId = id;
        this.customerName = name;
        this.foodItem = item;
    }

    public String toString() {
        return "Order ID: " + orderId + 
               ", Name: " + customerName + 
               ", Item: " + foodItem;
    }
}

// Main GUI Class
public class FoodOrderGUI extends JFrame {

    private JTextField nameField, itemField;
    private JTextArea displayArea;
    private Queue<Order> queue;
    private int orderId = 1;

    public FoodOrderGUI() {
        queue = new LinkedList<>();

        setTitle("Online Food Order System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input fields
        add(new JLabel("Customer Name:"));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Food Item:"));
        itemField = new JTextField(15);
        add(itemField);

        // Buttons
        JButton addButton = new JButton("Place Order");
        JButton viewButton = new JButton("View Orders");
        JButton processButton = new JButton("Process Order");

        add(addButton);
        add(viewButton);
        add(processButton);

        // Display area
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));

        // Button Actions

        // Place Order
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String item = itemField.getText();

            if (name.isEmpty() || item.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            queue.add(new Order(orderId++, name, item));
            displayArea.append("✅ Order Placed!\n");

            nameField.setText("");
            itemField.setText("");
        });

        // View Orders
        viewButton.addActionListener(e -> {
            displayArea.setText("");

            if (queue.isEmpty()) {
                displayArea.setText("No orders available.\n");
                return;
            }

            for (Order o : queue) {
                displayArea.append(o.toString() + "\n");
            }
        });

        // Process Order
        processButton.addActionListener(e -> {
            if (queue.isEmpty()) {
                displayArea.setText("No orders to process.\n");
                return;
            }

            Order o = queue.poll();
            displayArea.setText("🍳 Processing:\n" + o.toString() + "\n✅ Completed!\n");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new FoodOrderGUI();
    }
}