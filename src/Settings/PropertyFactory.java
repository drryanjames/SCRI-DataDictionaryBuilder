package Settings;

public class PropertyFactory {
	/**
	 * Gets a property instance from a string value
	 * @param propertyValue Property value
	 * @return Property instance from string
	 */
	public static IProperty GetProperty(String propertyValue)
	{
		if(propertyValue == null)
			return null;
		
		// Get the correct property
		IProperty property;
		if(propertyValue.startsWith(StringProperty.PropertyType))
			property = new StringProperty();
		else if(propertyValue.startsWith(BooleanProperty.PropertyType))
			property = new BooleanProperty();
		else
			return null;
		
		// If the value is loaded return the property
		if(property.load(propertyValue))
			return property;
		
		return null;
	}
}