package drawcode.graphics;

import com.vldocking.swing.docking.DockKey;
import com.vldocking.swing.docking.Dockable;

import javax.swing.*;
import java.awt.*;

class GeneratedCodeView extends JPanel implements Dockable {
    JTextArea textArea = new JTextArea("i = 9;");
    DockKey key = new DockKey("textEditor");

    public GeneratedCodeView() {
        setLayout(new BorderLayout());
        JScrollPane jsp = new JScrollPane(textArea);
        add(jsp, BorderLayout.CENTER);
        key.setName("Generated source code");
        key.setCloseEnabled(false);
        key.setFloatEnabled(true);
        textArea.setEditable(false);
    }

    public DockKey getDockKey() {
        return key;
    }

    @Override
    public Component getComponent() {
        return this;
    }
}