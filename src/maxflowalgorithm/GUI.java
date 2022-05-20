/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxflowalgorithm;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import static javafx.scene.input.KeyCode.G;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Mais
 */
public class GUI extends JFrame {

    JLabel nodeLabel;
    JLabel edgeLabel;
    JLabel sourceLabel;
    JLabel sinkLabel;
    JLabel AlgorithmLabel;
    JLabel resultLabel;

    int maxdfs;
    int maxbfs;

    JRadioButton bfscheck;
    JRadioButton dfscheck;
    ButtonGroup group;

    JTextField nodeField;
    JTextField edgeField;
    JTextField sourceField;
    JTextField sinkField;

    JButton GenerateButton;
    JButton InputButton;
    JButton MaxFlowButton;
    JButton exit;
    JButton clear;

    JSeparator sep;

    JLabel maxflowbfsLabel;
    JLabel timebfsLabel;

    JLabel maxflowdfsLabel;
    JLabel timedfsLabel;

    int numnodes;
    int numedges;
    int source;
    int sink;
    GraphMaxFlow graph;

    public GUI() {
        this.setVisible(true);
        this.setSize(800, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Max Flow Algorithm");
        this.setResizable(false);
        this.setLayout(null);

        // Create Components
        nodeLabel = new JLabel("number of vertices : ");

        edgeLabel = new JLabel("number of edges : ");

        sourceLabel = new JLabel("source : ");

        sinkLabel = new JLabel("sink : ");

        AlgorithmLabel = new JLabel("Max Flow Algorithm (BFS, DFS) ");
        AlgorithmLabel.setFont(new Font("Helvetics", Font.BOLD, 18));

        
        resultLabel = new JLabel("The Result ");
        resultLabel.setFont(new Font("Helvetics", Font.BOLD, 18));

        nodeField = new JTextField();
        edgeField = new JTextField();
        sourceField = new JTextField();
        sinkField = new JTextField();

        maxflowbfsLabel = new JLabel("The MaxFlow Of BFS is : ");
        timebfsLabel = new JLabel("The Time Of BFS is ");

        maxflowdfsLabel = new JLabel("The MaxFlow Of DFS is : ");
        timedfsLabel = new JLabel("The Time Of DFS is ");
        GenerateButton = new JButton("Generate Random Graph");

        GenerateButton.setBackground(Color.LIGHT_GRAY);
        GenerateButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        GenerateButton.setToolTipText("Generate Random Graph");
       
        InputButton = new JButton("Input Graph From File");
        InputButton.setBackground(Color.LIGHT_GRAY);
        InputButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        InputButton.setToolTipText("Input Graph From File");
       
        
        
        exit = new JButton("Exit");

        exit.setBackground(Color.LIGHT_GRAY);
        exit.setFont(new Font("Helvetica", Font.BOLD, 12));
        exit.setToolTipText("Exit From Project");

        clear = new JButton("Clear");

        clear.setBackground(Color.LIGHT_GRAY);
        clear.setFont(new Font("Helvetica", Font.BOLD, 12));
        clear.setToolTipText("Clear The Fields");

        MaxFlowButton = new JButton("FordFulkearson Algorithm");
        MaxFlowButton.setBackground(Color.LIGHT_GRAY);
        MaxFlowButton.setToolTipText("Run The Algorithm");
        MaxFlowButton.setFont(new Font("Helvetica", Font.BOLD, 12));

        bfscheck = new JRadioButton("Solve By BFS");
        dfscheck = new JRadioButton("Solve By DFS");

        group = new ButtonGroup();
        group.add(bfscheck);
        group.add(dfscheck);

        sep = new JSeparator();
     

        // Added Components
        this.add(nodeLabel);
        this.add(edgeLabel);
        this.add(sourceLabel);
        this.add(sinkLabel);
        this.add(maxflowbfsLabel);
        this.add(timebfsLabel);
        this.add(exit);
        this.add(clear);
        this.add(bfscheck);
        this.add(dfscheck);
        this.add(InputButton);

        this.add(maxflowdfsLabel);
        this.add(timedfsLabel);

        this.add(nodeField);
        this.add(edgeField);
        this.add(sourceField);
        this.add(sinkField);
        this.add(GenerateButton);
        this.add(resultLabel);
        this.add(sep);
        this.add(AlgorithmLabel);
        this.add(MaxFlowButton);

        // Set Location and size
        nodeLabel.setBounds(50, 50, 200, 30);
        edgeLabel.setBounds(210, 50, 200, 30);
        sourceLabel.setBounds(370, 50, 200, 30);
        sinkLabel.setBounds(530, 50, 200, 30);

        nodeField.setBounds(50, 80, 100, 30);
        edgeField.setBounds(210, 80, 100, 30);
        sourceField.setBounds(370, 80, 100, 30);
        sinkField.setBounds(530, 80, 100, 30);
        GenerateButton.setBounds(400, 150, 200, 30);
        InputButton.setBounds(100, 150, 200, 30);
        sep.setBounds(0, 200, 800, 30);

        AlgorithmLabel.setBounds(120, 180, 400, 80);
        resultLabel.setBounds(120, 260, 400, 70);

        maxflowbfsLabel.setBounds(10, 330, 200, 30);
        timebfsLabel.setBounds(10, 360, 200, 30);

        maxflowdfsLabel.setBounds(300, 330, 400, 30);
        timedfsLabel.setBounds(300, 360, 400, 30);

        MaxFlowButton.setBounds(400, 240, 185, 30);
        
        bfscheck.setBounds(20, 230, 150, 50);
        dfscheck.setBounds(220, 230, 150, 50);

        exit.setBounds(580, 400, 80, 20);
        clear.setBounds(680, 400, 80, 20);
       
      

        // Action Listener
       
        
        

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                graph = null;
                numedges = 0;
                numnodes = 0;
                source = 0;
                sink = 0;
                edgeField.setText("");
                nodeField.setText("");
                sinkField.setText("");
                sourceField.setText("");

                maxflowdfsLabel.setText("The MaxFlow Of DFS is : ");
                timedfsLabel.setText("The Time Of DFS is ");

                maxflowbfsLabel.setText("The MaxFlow Of BFS is : ");
                timebfsLabel.setText("The Time Of BFS is ");

            }
        });

        GenerateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String tmp = nodeField.getText();
                numnodes = Integer.parseInt(tmp);

                tmp = edgeField.getText();
                numedges = Integer.parseInt(tmp);

                tmp = sourceField.getText();
                source = Integer.parseInt(tmp);

                tmp = sinkField.getText();
                sink = Integer.parseInt(tmp);

                graph = new GraphMaxFlow(numnodes, source, sink);
                graph.generate(numedges);
            }
        });

        
        
        
        InputButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                try{
                    String path = JOptionPane.showInputDialog("input The Path of file");
                    FileReader file = new FileReader(path);
                    BufferedReader br = new BufferedReader(file);
                    String line = br.readLine();
                    String[] ele = line.split(" ");
                    numnodes = Integer.parseInt(ele[0]);
                    numedges = Integer.parseInt(ele[1]);
                    source = Integer.parseInt(ele[2]);
                    sink = Integer.parseInt(ele[3]);
                    graph = new GraphMaxFlow(numnodes, source, sink);
                    

                    while((line=br.readLine())!=null){
                        ele = line.split(" ");
                        int from = Integer.parseInt(ele[0]);
                        int to = Integer.parseInt(ele[1]);
                        int w = Integer.parseInt(ele[2]);
                        graph.capacity[from][to] = w;
                        
                    }
                    graph.showGraph();
                    
                    
                    
                }catch(Exception exception){
                }
                

                
                

              
             
                

                
                
            }
        });

        
        
        
        
        
        MaxFlowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (bfscheck.isSelected()) {

                    double firsttime = System.nanoTime();

                    graph.fordFulkersonBFS();

                    double endtime = System.nanoTime();
                    double duration = (double) (endtime - firsttime) / 100000000.0;
                    timebfsLabel.setText("The Time of BFS is " + Double.valueOf(duration));

                    maxflowbfsLabel.setText("The MaxFlow Of BFS is : " + String.valueOf(graph.maxFlow));

                } else if (dfscheck.isSelected()) {

                    double firsttime = System.nanoTime();

                    graph.fordFulkersonDFS();

                    double endtime = System.nanoTime();
                    double duration = (double) (endtime - firsttime) / 1000000000.0;
                    timedfsLabel.setText("The Time of DFS is " + Double.valueOf(duration));

                    maxflowdfsLabel.setText("The MaxFlow Of DFS is : " + String.valueOf(graph.maxFlow));

                } else {
                    JOptionPane.showMessageDialog(null, "Please Select The Algorithm First");
                }

            }
        });

    }

}
