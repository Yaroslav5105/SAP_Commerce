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
import de.hybris.platform.util.PartOfHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;
import org.training.jalo.Engine;

/**
 * Generated class for type {@link org.training.jalo.Car Car}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedCar extends GenericItem
{
	/** Qualifier of the <code>Car.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Car.engine</code> attribute **/
	public static final String ENGINE = "engine";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(ENGINE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Car.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Car.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Car.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Car.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Car.engine</code> attribute.
	 * @return the engine
	 */
	public Engine getEngine(final SessionContext ctx)
	{
		return (Engine)getProperty( ctx, ENGINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Car.engine</code> attribute.
	 * @return the engine
	 */
	public Engine getEngine()
	{
		return getEngine( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Car.engine</code> attribute. 
	 * @param value the engine
	 */
	public void setEngine(final SessionContext ctx, final Engine value)
	{
		new PartOfHandler<Engine>()
		{
			@Override
			protected Engine doGetValue(final SessionContext ctx)
			{
				return getEngine( ctx );
			}
			@Override
			protected void doSetValue(final SessionContext ctx, final Engine _value)
			{
				final Engine value = _value;
				setProperty(ctx, ENGINE,value);
			}
		}.setValue( ctx, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Car.engine</code> attribute. 
	 * @param value the engine
	 */
	public void setEngine(final Engine value)
	{
		setEngine( getSession().getSessionContext(), value );
	}
	
}
