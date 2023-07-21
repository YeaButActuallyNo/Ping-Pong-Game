import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class PingPongGame extends JPanel implements ActionListener, KeyListener {

    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int paddle1Y, paddle2Y; 
    private int paddleSpeed = 14; 
    
    private Timer timer; 

    private int ballSize = 10; 
    private int ballSpeedX = 3; 
    private int ballSpeedY = 3; 
    private int ballX, ballY; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ping Pong Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            PingPongGame pingPongGame = new PingPongGame();
            frame.add(pingPongGame);

            frame.pack();
            frame.setVisible(true);
        });
    }

    public PingPongGame() {

        Dimension temp = getPreferredSize(); 

        paddle1Y = temp.height / 2 - paddleHeight;
        paddle2Y = temp.height / 2 - paddleHeight;

        ballX = temp.width / 2 - ballSize; 
        ballY = temp.height / 2 - ballSize; 

        timer = new Timer(10, this);
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);

        //make the ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballSize, ballSize);
        
        //draw the paddles 
        g.setColor(Color.lightGray);
        g.fillRoundRect(getWidth()-paddleWidth, paddle1Y, paddleWidth, paddleHeight,5 , 5);
        g.fillRoundRect(0, paddle2Y, paddleWidth, paddleHeight, 5, 5);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screenSize.width, screenSize.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the game logic
        // For example, handle ball movement, collision detection, etc.
        
        ballX += ballSpeedX; 
        ballY += ballSpeedY; 

        //bounce off the walls
        if(ballX <= 0 || ballX + ballSize >= getWidth()) {
            ballSpeedX *= -1;
        } else if (ballY <= 0 || ballY + ballSize >= getHeight()) {
            ballSpeedY *= -1; 
        }

        //bounce off the paddles
        

        // Repaint the panel to reflect the updated game state
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            paddle1Y -= paddleSpeed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            paddle1Y += paddleSpeed;
        } else if (keyCode == KeyEvent.VK_W) {
            paddle2Y -= paddleSpeed;
        } else if (keyCode == KeyEvent.VK_S) {
            paddle2Y += paddleSpeed;
        }


        if (paddle1Y < 0) {
            paddle1Y = 0;
        } else if (paddle1Y + paddleHeight > getHeight()) {
            paddle1Y = getHeight() - paddleHeight;
        }

        if (paddle2Y < 0) {
            paddle2Y = 0;
        } else if (paddle2Y + paddleHeight > getHeight()) {
            paddle2Y = getHeight() - paddleHeight;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
 
}