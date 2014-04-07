package Settings.UI;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Settings.IProperty;
import Settings.PropertiesCollection;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class PropertiesCollectionJPanel extends JPanel {

	public PropertiesCollectionJPanel(PropertiesCollection propertiesCollection)
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JPanel panelContent = new JPanel();
		scrollPane.add(panelContent);
		scrollPane.setViewportView(panelContent);
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		
		// Create the settings title
		JLabel lblTitle = new JLabel("Settings");
		panelContent.add(lblTitle);
		
		// Create property component container
		final JPanel panelPropertyComponentContainer = new JPanel();
		panelContent.add(panelPropertyComponentContainer);
		panelPropertyComponentContainer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelPropertyComponentContainer.setLayout(new BoxLayout(panelPropertyComponentContainer, BoxLayout.Y_AXIS));
		
		// Create a component for each property and add it to the container
		addPropertyComponentsFromPropertiesCollection(panelPropertyComponentContainer, propertiesCollection);
		
		// Create the save button
		JButton btnSave = new JButton("Save Settings");
		panelContent.add(btnSave);
		final PropertiesCollection finalPropertiesCollection = propertiesCollection;
		btnSave.addActionListener(new ActionListener() {
			/**
			 * Save the properties collection when the property is clicked
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(finalPropertiesCollection != null)
				{
					// Save all the properties
					finalPropertiesCollection.Save();
					
					// Refresh all the data
					panelPropertyComponentContainer.removeAll();
					PropertiesCollectionJPanel.this.addPropertyComponentsFromPropertiesCollection(panelPropertyComponentContainer, finalPropertiesCollection);
					
					// Refresh root frame so the entry shows up
					JFrame frame = (JFrame) SwingUtilities.getRoot(panelPropertyComponentContainer);
					frame.validate();
					frame.repaint();
				}
			}
		});
	}
	
	/**
	 * Create a property component for each property in the collection and add it to the content pane
	 * @param propertiesCollection
	 */
	private void addPropertyComponentsFromPropertiesCollection(JPanel panelPropertyComponentContainer, PropertiesCollection propertiesCollection)
	{
		if(propertiesCollection == null)
			return;
		
		// Get a UI component for each property and add it to the content pane
		for(IProperty property : propertiesCollection.getProperties())
		{
			// Get the property component
			JComponent propertyComponenet = PropertyComponentFactory.GetComponentForProperty(property);
			
			// If the property component is valid add it to the content pane
			if(propertyComponenet != null)
				panelPropertyComponentContainer.add(propertyComponenet);
		}
	}
}
