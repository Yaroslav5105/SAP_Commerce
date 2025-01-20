/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 20, 2025, 4:07:01 PM                    ---
 * ----------------------------------------------------------------
 */
package org.training.core.jalo;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.user.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.user.User CarWowUser}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedCarWowUser extends User
{
	/** Qualifier of the <code>CarWowUser.favoriteCarBrand</code> attribute **/
	public static final String FAVORITECARBRAND = "favoriteCarBrand";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(User.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FAVORITECARBRAND, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CarWowUser.favoriteCarBrand</code> attribute.
	 * @return the favoriteCarBrand
	 */
	public EnumerationValue getFavoriteCarBrand(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, FAVORITECARBRAND);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CarWowUser.favoriteCarBrand</code> attribute.
	 * @return the favoriteCarBrand
	 */
	public EnumerationValue getFavoriteCarBrand()
	{
		return getFavoriteCarBrand( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CarWowUser.favoriteCarBrand</code> attribute. 
	 * @param value the favoriteCarBrand
	 */
	public void setFavoriteCarBrand(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, FAVORITECARBRAND,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CarWowUser.favoriteCarBrand</code> attribute. 
	 * @param value the favoriteCarBrand
	 */
	public void setFavoriteCarBrand(final EnumerationValue value)
	{
		setFavoriteCarBrand( getSession().getSessionContext(), value );
	}
	
}
