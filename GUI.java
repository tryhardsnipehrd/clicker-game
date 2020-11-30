import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Scanner;

public class GUI {
    //Define everything for later, this is gonna get crazy

    private BigInteger currentClicks = new BigInteger("0");
    private BigInteger clickMultiplier = new BigInteger("1");
    private BigInteger lifetimeClicks = new BigInteger("0");
    private BigInteger shopMultiplier = new BigInteger("0");
    private BigInteger lifetimeWithMultiplier = new BigInteger("0");
    private int autoSpeed = 1;
    private BigInteger autoClickers = new BigInteger("0");
    private BigInteger autoClickerMultiplier = new BigInteger("1");
    private final BigInteger one = new BigInteger("1");
    private final BigInteger hundred = new BigInteger("100");
    private final BigInteger fifty = new BigInteger("50");
    private final BigInteger twentyFive = new BigInteger("25");
    private int compareThing;


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
            lifetimeWithMultiplier = lifetimeWithMultiplier.add(autoClickerMultiplier.multiply(autoClickers));
            currentClicks = currentClicks.add(autoClickerMultiplier.multiply(autoClickers));
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
                        currentClicks = currentClicks.add(clickMultiplier);
                        lifetimeWithMultiplier = lifetimeWithMultiplier.add(clickMultiplier);
                        lifetimeClicks = lifetimeClicks.add(one);
                        cash.setText("Number of clicks: " + currentClicks);
                        lifetime.setText("Lifetime clicks: " + lifetimeClicks);
                        lifetimeMulti.setText("Lifetime Earnings: "+lifetimeWithMultiplier);
                        System.out.println(currentClicks);
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        compareThing = currentClicks.compareTo(hundred.add(fifty.multiply(shopMultiplier)));
                        if (compareThing == -1){
                            currentClicks = currentClicks.subtract(hundred.add(fifty.multiply(shopMultiplier)));
                            cash.setText("Number of clicks: " + currentClicks);
                            shop.setText("Upgrade your clicks: "+clickMultiplier);
                            shopMultiplier = shopMultiplier.add(one);
                            clickMultiplier = clickMultiplier.add(one);
                        }

                    }
                }
        );
        upgradeAutoClickers.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        compareThing = currentClicks.compareTo(hundred.add(hundred.multiply(autoClickerMultiplier)));
                        if (compareThing == -1){
                            currentClicks = currentClicks.subtract(hundred.add(hundred.multiply(autoClickerMultiplier)));
                            cash.setText("Number of clicks: " + currentClicks);
                            upgradeAutoClickers.setText("Upgrade AutoClickers: "+autoClickerMultiplier);
                            autoClickerMultiplier = autoClickerMultiplier.add(one);
                        }
                    }
                }
        );
        buyAutoClickers.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        compareThing = currentClicks.compareTo(hundred.add(twentyFive.multiply(autoClickers)));
                        if (compareThing == -1) {
                            currentClicks = currentClicks.subtract(hundred.add(twentyFive.multiply(autoClickers)));
                            cash.setText("Number of clicks: " + currentClicks);
                            buyAutoClickers.setText("Buy more AutoClickers: "+autoClickers);
                            autoClickers = autoClickers.add(one);
                            if (autoClickers==one) {
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

        // Anything for debugging can go here

        // Tell the program to go to the GUI instead of here, so that I can manage it better
        new GUI();

    }
}
