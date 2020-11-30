import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    //Define everything for later, this is gonna get crazy

    private long currentClicks = 0;
    private int clickMultiplier = 1;
    private long lifetimeClicks = 0;
    private int shopMultiplier = 0;
    private int lifetimeWithMultiplier = 0;
    private int autoSpeed = 1;
    private int autoClickers = 0;
    private int autoClickerMultiplier = 1;


    private JLabel cash;
    private JLabel lifetime;
    private JFrame frame;
    private JButton clicker;
    private JPanel panel;
    private JButton shop;
    private JLabel lifetimeMulti;
    private JButton upgradeAutoClickers;
    private JButton buyAutoClickers;

    // Defining the action cause I have to for this to be much easier
    ActionListener autoClickerAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            lifetimeWithMultiplier += autoClickerMultiplier * autoClickers;
            currentClicks += autoClickerMultiplier * autoClickers;
            lifetimeMulti.setText("Lifetime Earnings: " + lifetimeWithMultiplier);
            cash.setText("Number of clicks: "+currentClicks);
        }
    };
    private Timer autoClicker = new Timer(1000/autoSpeed, autoClickerAction);

    public GUI() {
        // Now that everything is created, actually make them usable

        frame = new JFrame();
        shop = new JButton("Upgrade your clicks");
        clicker = new JButton("Click Me");
        lifetime = new JLabel("Lifetime clicks: "+lifetimeClicks, SwingConstants.CENTER);
        lifetimeMulti = new JLabel("Lifetime Earnings: "+lifetimeWithMultiplier, SwingConstants.CENTER);
        upgradeAutoClickers = new JButton("Upgrade the AutoClickers");
        buyAutoClickers = new JButton("Buy more AutoClickers");

        cash = new JLabel("Number of clicks: 0", SwingConstants.CENTER);
        panel = new JPanel();
        // This is where I found the answer for separate buttons
        // https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing/5911621
        clicker.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        currentClicks += clickMultiplier;
                        lifetimeWithMultiplier += clickMultiplier;
                        lifetimeClicks++;
                        cash.setText("Number of clicks: " + currentClicks);
                        lifetime.setText("Lifetime clicks: " + lifetimeClicks);
                        lifetimeMulti.setText("Lifetime Earnings: "+lifetimeWithMultiplier);
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentClicks >= 100+(50*shopMultiplier)){
                            currentClicks -= 100 + (50*shopMultiplier);
                            cash.setText("Number of clicks: " + currentClicks);
                            shop.setText("Upgrade your clicks: "+clickMultiplier);
                            shopMultiplier++;
                            clickMultiplier++;
                        }

                    }
                }
        );
        upgradeAutoClickers.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentClicks >= 100 + (autoClickerMultiplier*100)){
                            currentClicks-= 100 + (autoClickerMultiplier*100);
                            cash.setText("Number of clicks: " + currentClicks);
                            upgradeAutoClickers.setText("Upgrade AutoClickers: "+autoClickerMultiplier);
                            autoClickerMultiplier++;
                        }
                    }
                }
        );
        buyAutoClickers.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (currentClicks >= 100 + (autoClickers*25)) {
                            currentClicks -= 100 + (autoClickers*25);
                            cash.setText("Number of clicks: " + currentClicks);
                            buyAutoClickers.setText("Buy more AutoClickers: "+autoClickers);
                            autoClickers++;
                            if (autoClickers==1) {
                                autoClicker.start();
                            }
                        }
                    }
                }
        );

        // Actually create the window, specifically the contents of the panel
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(4, 2));
        panel.add(clicker);
        panel.add(shop);
        panel.add(buyAutoClickers);
        panel.add(upgradeAutoClickers);
        panel.add(cash);
        panel.add(lifetime);
        panel.add(lifetimeMulti);

        // Initialize the window with the panel, and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clicker Game");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        // Tell the program to go to the GUI instead of here, so that I can manage it better
        new GUI();

    }
}
