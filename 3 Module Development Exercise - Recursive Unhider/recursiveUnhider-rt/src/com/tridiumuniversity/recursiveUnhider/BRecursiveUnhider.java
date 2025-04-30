package com.tridiumuniversity.recursiveUnhider;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(name="regex",type = "BString",defaultValue = "BString.make(\".*\")", flags = Flags.SUMMARY)
@NiagaraAction(name="unhide")
public class BRecursiveUnhider extends BComponent {




  public void doUnhide() {
        unhideHiddenFlagsOnComponents(Sys.getStation());
  }

  private void unhideHiddenFlagsOnComponents(BComponent component) {
      Slot[] slotsArray = component.getSlotsArray();
      for (int i = 0; i < slotsArray.length; i++) { //iterate over all slots
            if (slotsArray[i].getName().matches(getRegex())) {
                System.out.println("Unhiding " + component.getSlotPath() + "\\" + slotsArray[i].getName());
                component.setFlags(slotsArray[i], Flags.remove(component.getFlags(slotsArray[i]), Flags.HIDDEN));
            }
      }

      BComponent[] childComponents = component.getChildComponents(); // iterate over all childcomponents
      for (int i = 0; i < childComponents.length; i++) {
        unhideHiddenFlagsOnComponents(childComponents[i]);;
      }
    }








//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.recursiveUnhider.BRecursiveUnhider(791081917)1.0$ @*/
/* Generated Thu Apr 24 12:30:54 BST 2025 by Slot-o-Matic (c) Tridium, Inc. 2012-2025 */

  //region Property "regex"

  /**
   * Slot for the {@code regex} property.
   * @see #getRegex
   * @see #setRegex
   */
  public static final Property regex = newProperty(Flags.SUMMARY, BString.make(".*"), null);

  /**
   * Get the {@code regex} property.
   * @see #regex
   */
  public String getRegex() { return getString(regex); }

  /**
   * Set the {@code regex} property.
   * @see #regex
   */
  public void setRegex(String v) { setString(regex, v, null); }

  //endregion Property "regex"

  //region Action "unhide"

  /**
   * Slot for the {@code unhide} action.
   * @see #unhide()
   */
  public static final Action unhide = newAction(0, null);

  /**
   * Invoke the {@code unhide} action.
   * @see #unhide
   */
  public void unhide() { invoke(unhide, null, null); }

  //endregion Action "unhide"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BRecursiveUnhider.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/





}
