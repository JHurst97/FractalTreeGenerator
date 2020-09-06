package treeGenerator;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RandomTree extends JComponent {

	JPanel panel = new JPanel();
	private static Color branchColor = Color.WHITE;
	private static Color leafColor1  = Color.WHITE;
	private static Color leafColor2  = Color.WHITE;
	static String numberBranches       = "9";
	public static int branchCounter    = 12;
	public static int recursionCounter = 2;
	public static int lengthCounter    = 20;
	public static int angleCounter     = 19;
	public static boolean isRandom;
	static JTextArea logTA;

	public RandomTree() {
		setBounds(00, 00, 1000, 1000);
		this.add(panel);
	}

	int branchCount = 0;

	private void drawTree(Graphics g, int x1, int y1, double angle, int depth) {
		if (depth == 0)
			return;

		int x2;
		int y2;

		if (isRandom == true) {
			x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * 1 * depth * Math.random() * lengthCounter);
			y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * 1 * depth * Math.random() * lengthCounter);
		} else {
			x2 = x1 + (int) ((Math.cos(Math.toRadians(angle)) * 1 * depth * lengthCounter) / 2);
			y2 = y1 + (int) ((Math.sin(Math.toRadians(angle)) * 1 * depth * lengthCounter) / 2);
		}
		g.setColor(branchColor);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(10));
		if (depth >= 0 && depth <= 3) {

			if (depth % 2 == 0) {
				g.setColor(leafColor1);
				g2.setStroke(new BasicStroke(2));
			} else {
				g.setColor(leafColor2);
				g2.setStroke(new BasicStroke(2));
			}
		} else if (depth >= 4 && depth <= 6) {

			g2.setStroke(new BasicStroke(3));

		} else if (depth >= 7 && depth <= 9) {

			g2.setStroke(new BasicStroke(5));
		} else if (depth >= 10 && depth <= 13) {

			g2.setStroke(new BasicStroke(10));
		}

		g2.drawLine(x1, y1, x2, y2);

		if (recursionCounter == 0) {

		} else if (recursionCounter == 1) {
			drawTree(g, x2, y2, angle + angleCounter, depth - 1);

		} else if (recursionCounter == 2) {
			drawTree(g, x2, y2, angle - angleCounter, depth - 1);
			drawTree(g, x2, y2, angle + angleCounter, depth - 1);

		} else if (recursionCounter == 3) {
			drawTree(g, x2, y2, angle - angleCounter, depth - 1);
			drawTree(g, x2, y2, angle + 0, depth - 1);
			drawTree(g, x2, y2, angle + angleCounter, depth - 1);

		} else if (recursionCounter == 4) {
			drawTree(g, x2, y2, angle - angleCounter, depth - 1);
			drawTree(g, x2, y2, angle - (angleCounter / 2), depth - 1);
			drawTree(g, x2, y2, angle + (angleCounter / 2), depth - 1);
			drawTree(g, x2, y2, angle + angleCounter, depth - 1);

		} else if (recursionCounter == 5) {
			drawTree(g, x2, y2, angle - (angleCounter / 2), depth - 1);
			drawTree(g, x2, y2, angle - (angleCounter / 0.5), depth - 1);
			drawTree(g, x2, y2, angle + 0, depth - 1);
			drawTree(g, x2, y2, angle + (angleCounter / 0.5), depth - 1);
			drawTree(g, x2, y2, angle + (angleCounter / 2), depth - 1);

		} else
			branchCount++;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(branchColor);
		g2.setStroke(new BasicStroke(10));
		g2.drawLine(this.getWidth() / 2, this.getHeight(), this.getWidth() / 2, this.getHeight() - 50);

		for (int i = 0; i < 1; i++) {

			g2.setStroke(new BasicStroke(11));
			drawTree(g, this.getWidth() / 2, this.getHeight() - 50, -90, branchCounter);
			System.out.println("new tree!");
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("hello");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);

		RandomTree randomTree = new RandomTree();
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, randomTree);
		frame.add(sp);
		sp.setDividerLocation(250);
		sp.setDividerSize(0);

		JLabel chooseLabel = new JLabel("Choose... ");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(chooseLabel, c);

		JButton reproduce = new JButton("Regrow!!");
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(reproduce, c);

		JLabel chooseBranch = new JLabel("branch colour:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(chooseBranch, c);

		JButton cBranchC = new JButton("pick...");
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(cBranchC, c);

		JLabel chooseLeaf1 = new JLabel("leaf colour 1:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(chooseLeaf1, c);

		JButton cLeafC1 = new JButton("pick...");
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		panel.add(cLeafC1, c);

		JLabel chooseLeaf2 = new JLabel("leaf colour 2:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(chooseLeaf2, c);

		JButton cLeafC2 = new JButton("pick...");
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(cLeafC2, c);

		// number of branches
		JLabel chooseNumberBranch = new JLabel("Branches:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		panel.add(chooseNumberBranch, c);

		SpinnerModel value = new SpinnerNumberModel(12, // initial value
				0, // minimum value
				20, // maximum value
				1); // step
		JSpinner spinner = new JSpinner(value);
		spinner.setBounds(100, 100, 50, 30);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				branchCounter = (Integer) spinner.getValue();
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);

					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);

					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}

				logTA.append("\nbranches: " + branchCounter);
			}
		});

		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(spinner, c);

		// number of recursions
		JLabel recursionLabel = new JLabel("Density: ");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		panel.add(recursionLabel, c);

		SpinnerModel value1 = new SpinnerNumberModel(2, // initial value
				0, // minimum value
				5, // maximum value
				1); // step
		JSpinner spinner1 = new JSpinner(value1);
		spinner1.setBounds(100, 100, 50, 30);
		spinner1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				recursionCounter = (Integer) spinner1.getValue();
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}

				logTA.append("\nDensity: " + recursionCounter);
			}
		});
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 5;
		panel.add(spinner1, c);

		// branch length
		JLabel lengthLabel = new JLabel("Length: ");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		panel.add(lengthLabel, c);

		SpinnerModel value2 = new SpinnerNumberModel(20, // initial value
				0, // minimum value
				20, // maximum value
				1); // step
		JSpinner spinner2 = new JSpinner(value2);
		spinner2.setBounds(100, 100, 50, 30);
		spinner2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				lengthCounter = (Integer) spinner2.getValue();
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}

				logTA.append("\nlength: " + lengthCounter);
			}
		});
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 6;
		panel.add(spinner2, c);

		// branch angle
		JLabel angleLabel = new JLabel("Angle: ");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		panel.add(angleLabel, c);

		SpinnerModel value3 = new SpinnerNumberModel(19, // initial value
				0, // minimum value
				80, // maximum value
				2); // step
		JSpinner spinner3 = new JSpinner(value3);
		spinner3.setBounds(100, 100, 50, 30);
		spinner3.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				angleCounter = (Integer) spinner3.getValue();
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}

				logTA.append("\nangle: " + angleCounter);
			}
		});
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 7;
		panel.add(spinner3, c);

		// random?

		JRadioButton strButton = new JRadioButton("structured recursive", true);

		JRadioButton ranButton = new JRadioButton("random recursive");

		// ... Create a button group and add the buttons.
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(strButton);
		bgroup.add(ranButton);

		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 8;
		panel.add(strButton, c);

		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 9;
		panel.add(ranButton, c);

		// log

		JLabel logLabel = new JLabel("log:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 10;
		panel.add(logLabel, c);

		
		logTA = new JTextArea("");
		logTA.setEditable(false);
		logTA.setRows(50);
		logTA.setLineWrap(true);
		logTA.setWrapStyleWord(true);
		JScrollPane scrP = new JScrollPane(logTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panel.add(scrP);
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 3;
		panel.add(scrP, c);

		frame.setSize(1910, 1070);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		reproduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				logTA.append("\n tree wiped -> new tree");
			}
		});

		cBranchC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				branchColor = JColorChooser.showDialog(null, "pick your colour", branchColor);
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				logTA.append("\n branch colour set! -" + branchColor);
			}
		});

		cLeafC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				leafColor1 = JColorChooser.showDialog(null, "pick your colour", leafColor1);
				if (frame.getHeight() == 800) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				logTA.append("\n leaf 1 colour set! -" + leafColor1);
			}
		});

		cLeafC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				leafColor2 = JColorChooser.showDialog(null, "pick your colour", leafColor2);
				if (frame.getHeight() == 1069) {
					frame.setSize(1910, 1069);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					frame.setSize(1910, 1070);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				logTA.append("\n leaf 2 colour set! -" + leafColor2);
			}
		});

		strButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				isRandom = false;

				if (isRandom == true) {

					logTA.append("true");

				} else {

					logTA.append("false");

				}

			}
		});

		ranButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				isRandom = true;

				if (isRandom == true) {

					logTA.append("true");

				} else {

					logTA.append("false");

				}
			}
		});

	}

}
