package drawcode.graphics;

import javax.swing.*;

import com.vldocking.swing.docking.*;
import drawcode.model.Graph;
import drawcode.model.Node;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawCode extends JFrame{

    private final Graph graph = new Graph();
    private final GeneratedCodeView editorPanel = new GeneratedCodeView();
    private final ElementListView listView = new ElementListView(graph);
    private final DockingDesktop desk = new DockingDesktop();
    private final GraphView graphView;
    private final JMenuBar menuBar = new JMenuBar();
    private final ArrayList<View> views = new ArrayList<View>();

    public DrawCode(){
        setLayout(new BorderLayout());
        graphView = new GraphView(graph, this);
        ScreenCoordinates.INSTANCE.setGraph(graph);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JMenu fileMenu = new JMenu("File");
        JMenu drawMenu = new JMenu("Draw");
        JMenuItem drawArrow = new JMenuItem("Draw arrow");
        drawArrow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Node firstSelectedNode = graphView.getSelectedNodes().getFirst();
                Node secondSelectedNode = graphView.getSelectedNodes().getSecond();
                graph.addEdge(firstSelectedNode, secondSelectedNode);
                refreshViews();
            }
        });
        drawMenu.add(drawArrow);
        menuBar.add(fileMenu);
        menuBar.add(drawMenu);

        setJMenuBar(menuBar);
        getContentPane().add(desk); //getContentPane().add is equivalent to add()

        views.add(graphView);
        views.add(listView);
        views.add(ScreenCoordinates.INSTANCE);

        desk.addDockable(graphView);
        desk.split(graphView, listView, DockingConstants.SPLIT_LEFT); //Calling split() makes adding the other swing components unnecessary
        desk.split(graphView, editorPanel, DockingConstants.SPLIT_RIGHT);

        desk.setDockableWidth(graphView, 0.5);
        desk.setDockableWidth(editorPanel, 0.25);
        desk.setDockableWidth(listView, 0.25);
    }

    public void refreshViews(){
        for(View view : views){
            view.refresh();
        }
    }

    public void registerListener(View view){
        this.views.add(view);
    }

    public static void main(String[] args){
        final DrawCode frame = new DrawCode();
        frame.setSize(1200,800);
        frame.validate();
        SwingUtilities.invokeLater(new Runnable(){
            // in the event dispatch thread
            public void run(){
                frame.setVisible(true);
            }
        });
    }
}
