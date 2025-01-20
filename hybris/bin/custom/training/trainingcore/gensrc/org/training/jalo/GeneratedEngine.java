/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 20, 2025, 4:07:01 PM                    ---
 * ----------------------------------------------------------------
 */
package org.training.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;

/**
 * Generated class for type {@link org.training.jalo.Engine Engine}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedEngine extends GenericItem
{
	/** Qualifier of the <code>Engine.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Engine.horsepower</code> attribute **/
	public static final String HORSEPOWER = "horsepower";
	/** Qualifier of the <code>Engine.name</code> attribute **/
	public static final String NAME = "name";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(HORSEPOWER, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.horsepower</code> attribute.
	 * @return the horsepower
	 */
	public Integer getHorsepower(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, HORSEPOWER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.horsepower</code> attribute.
	 * @return the horsepower
	 */
	public Integer getHorsepower()
	{
		return getHorsepower( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.horsepower</code> attribute. 
	 * @return the horsepower
	 */
	public int getHorsepowerAsPrimitive(final SessionContext ctx)
	{
		Integer value = getHorsepower( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.horsepower</code> attribute. 
	 * @return the horsepower
	 */
	public int getHorsepowerAsPrimitive()
	{
		return getHorsepowerAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.horsepower</code> attribute. 
	 * @param value the horsepower
	 */
	public void setHorsepower(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, HORSEPOWER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.horsepower</code> attribute. 
	 * @param value the horsepower
	 */
	public void setHorsepower(final Integer value)
	{
		setHorsepower( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.horsepower</code> attribute. 
	 * @param value the horsepower
	 */
	public void setHorsepower(final SessionContext ctx, final int value)
	{
		setHorsepower( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.horsepower</code> attribute. 
	 * @param value the horsepower
	 */
	public void setHorsepower(final int value)
	{
		setHorsepower( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Engine.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Engine.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
}
