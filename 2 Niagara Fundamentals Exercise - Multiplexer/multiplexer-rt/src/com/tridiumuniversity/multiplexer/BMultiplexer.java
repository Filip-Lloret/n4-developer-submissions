package com.tridiumuniversity.multiplexer;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.BBoolean;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
@NiagaraProperty(name = "out", type = "BBoolean",defaultValue = "BBoolean.FALSE",flags = Flags.SUMMARY)
public class BMultiplexer extends BComponent {

    @Override
    public void changed(Property property, Context context) {

        int numberOfInputs = getNumberOfInputs();
        int totalNumberOfInputs = (int) Math.pow(2,numberOfInputs);

        int selectedInput = 0;
        // select output based on number
        for (int i = 0; i < totalNumberOfInputs; i++) {
            if (getProperty("s" + i) != null) {
                boolean aBoolean = getBoolean(getProperty("s" + i));
                if (aBoolean) {
                    selectedInput = selectedInput + ((int) Math.pow(2, i));
                }
            }
        }

        System.out.println("Selected Input: " + selectedInput);
        if (getProperty("in" + selectedInput) != null) {
            setOut(getBoolean(getProperty("in" + selectedInput)));
        }
    }

    private int getNumberOfInputs() {
        Property[] propertiesArray = getPropertiesArray();
        int numberOfInputs = 0;
        for (int i = 0; i < propertiesArray.length; i++) {
            if (propertiesArray[i].getName().startsWith("in")) {
                numberOfInputs++;
            }
        }
        return numberOfInputs;
    }

//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.multiplexer.BMultiplexer(12912206)1.0$ @*/
/* Generated Thu Apr 24 11:16:50 BST 2025 by Slot-o-Matic (c) Tridium, Inc. 2012-2025 */

  //region Property "out"

  /**
   * Slot for the {@code out} property.
   * @see #getOut
   * @see #setOut
   */
  public static final Property out = newProperty(Flags.SUMMARY, BBoolean.FALSE.as(BBoolean.class).getBoolean(), null);

  /**
   * Get the {@code out} property.
   * @see #out
   */
  public boolean getOut() { return getBoolean(out); }

  /**
   * Set the {@code out} property.
   * @see #out
   */
  public void setOut(boolean v) { setBoolean(out, v, null); }

  //endregion Property "out"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BMultiplexer.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/





}
