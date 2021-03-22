import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.Random;

public class ClientInterface implements DocumentListener, KeyListener {

    int user_id;
    String queueName;
    boolean isTyping = true;
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    Color randomColor = new Color(r, g, b);

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }


    ClientInterface(int user_id, String queueName) throws IOException, TimeoutException {
        this.user_id = user_id;
        this.queueName = queueName;

        JPanel jPanel = new JPanel();
        jPanel.setBorder(new TitledBorder(new EtchedBorder(), "User"+user_id));
        jPanel.setBounds(10,30,800,600);
        JFrame f= new JFrame("Text Editor of the user"+ user_id);


        // USER A
        JTextArea area=new JTextArea(20,20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);
        area.setFont(fieldFont);
        area.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        area.addKeyListener(this);
        area.getDocument().addDocumentListener(this);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setForeground(randomColor);


        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 11, 455, 249);


        // Listening to other queue
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            this.isTyping = false;
            area.setText(receivedMessage);
            area.setCaretPosition(area.getText().length());
            System.out.println(" [x] sent '"+receivedMessage+" '");

        };
        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});

        jPanel.add(scroll);
        f.add(jPanel);
        f.setSize(830,670);
        f.setLayout(null);
        f.setVisible(true);



    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            System.out.println("USER"+ this.user_id +"TYPING");
            if(this.isTyping) {
                String text = e.getDocument().getText(0,e.getDocument().getLength());
                Send sender = new Send(text,queueName);
                sender.sendMessage();
            }
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            if(this.isTyping) {
                String text = e.getDocument().getText(0,e.getDocument().getLength());
                Send sender = new Send(text,queueName);
                sender.sendMessage();
            }
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("CHANGED UPDATE");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.isTyping = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.isTyping = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.isTyping = false;
    }
}
