/*
 * Version info:
 *     $HeadURL: https://cscs-repast-demos.googlecode.com/svn/richard/StupidModel/tags/2011_05_29_model_03/src/stupidmodel/common/Constants.java $
 *     $LastChangedDate: 2011-05-29 19:01:44 +0200 (V, 29 máj. 2011) $
 *     $LastChangedRevision: 182 $
 *     $LastChangedBy: richard.legendi@gmail.com $
 */
package stupidmodel.common;

import stupidmodel.StupidModelContextBuilder;
import stupidmodel.agents.Bug;

/**
 * Dedicated class to store some model-specific constant values (e.g. bug vision
 * ranges and context IDs) that is uninstantiable.
 * 
 * <p>
 * The different IDs are usually set in
 * {@link StupidModelContextBuilder#build(repast.simphony.context.Context)} and
 * used to access the shared structures (<code>Grid</code>, <code>Space</code>
 * or <code>Context</code>) easily.
 * </p>
 * 
 * <p>
 * Final class, cannot be extended.
 * </p>
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @since 2.0-beta, 2011
 * @version $Id: Constants.java 149 2011-05-26 19:03:50Z
 *          richard.legendi@gmail.com $
 */
public final class Constants {

	/** The root <code>Context</code> ID. */
	public static final String CONTEXT_ID = "StupidModel";

	/** The <code>Space</code> ID. */
	public static final String SPACE_ID = "space";

	/** The <code>Grid</code> ID. */
	public static final String GRID_ID = "grid";

	/** The first model specified a two-dimensional grid <code>100 * 100</code>. */
	public static final int GRID_SIZE = 100;

	/**
	 * The first model specified to create <code>100</code> {@link Bug} agents.
	 */
	public static final int BUG_COUNT = 100;

	/**
	 * The first model specified an agent vision range of <code>4 * 4</code>
	 * cells.
	 */
	public static final int BUG_VISION_RANGE = 4;

	/** A bug grows by a fixed amount of size in each time step. */
	public static final double BUG_GROWTH_RATE = 1.0;

	/**
	 * Unique ID for the food availability layer.
	 * 
	 * @since Model 3
	 */
	public static final String FOOD_VALUE_LAYER_ID = "foodValueLayer";

	/**
	 * Hidden constructor to ensure no instances are created.
	 */
	private Constants() {
		;
	}

}
