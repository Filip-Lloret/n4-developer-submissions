package com.tridiumuniversity.checksumService;

import javax.baja.control.trigger.BTimeTrigger;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.util.BIRestrictedComponent;
import java.util.concurrent.ExecutorService;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@NiagaraType
@NiagaraProperty(name = "checksum",type = "BString",defaultValue = "")
@NiagaraProperty(name="trigger",type = "BTimeTrigger",defaultValue = "new BTimeTrigger()")
@NiagaraAction(name="generateChecksum", flags = Flags.HIDDEN)
public class BChecksumService extends BAbstractService implements BIRestrictedComponent {

    private ExecutorService executor;

    @Override
    public void started() {
        if (this.get("link") == null) {
            BLink link = new BLink(getTrigger().getHandleOrd(),"fireTrigger","generateChecksum",true);
            this.add("link", link);
        }
        executor = newSingleThreadExecutor();
    }

    public Type[] getServiceTypes() {
        return new Type[]{BChecksumService.TYPE};
    }


    public void doGenerateChecksum() {
        if (this.getEnabled()) {
            executor.execute(this::createChecksum);
        }
    }

    private void createChecksum() {
        Checksum crc32 = new CRC32();
        iterateOverComponentAndChildrenAndCalculateChecksum(Sys.getStation(), crc32);
        long checksum = crc32.getValue();
        setChecksum(Long.toHexString(checksum));
    }


    private void iterateOverComponentAndChildrenAndCalculateChecksum(BComponent component,Checksum checksum) {
        checksum.update(component.getHandleOrd().toString().getBytes(),0,component.getHandleOrd().toString().length());
        BComponent[] childComponents = component.getChildComponents(); // iterate over all childcomponents
        for (int i = 0; i < childComponents.length; i++) {
            iterateOverComponentAndChildrenAndCalculateChecksum(childComponents[i],checksum);
        }
    }


    @Override
    public void stopped() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }


//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.checksumService.BChecksumService(21973052)1.0$ @*/
/* Generated Fri Apr 25 15:10:44 BST 2025 by Slot-o-Matic (c) Tridium, Inc. 2012-2025 */

  //region Property "checksum"

  /**
   * Slot for the {@code checksum} property.
   * @see #getChecksum
   * @see #setChecksum
   */
  public static final Property checksum = newProperty(0, "", null);

  /**
   * Get the {@code checksum} property.
   * @see #checksum
   */
  public String getChecksum() { return getString(checksum); }

  /**
   * Set the {@code checksum} property.
   * @see #checksum
   */
  public void setChecksum(String v) { setString(checksum, v, null); }

  //endregion Property "checksum"

  //region Property "trigger"

  /**
   * Slot for the {@code trigger} property.
   * @see #getTrigger
   * @see #setTrigger
   */
  public static final Property trigger = newProperty(0, new BTimeTrigger(), null);

  /**
   * Get the {@code trigger} property.
   * @see #trigger
   */
  public BTimeTrigger getTrigger() { return (BTimeTrigger)get(trigger); }

  /**
   * Set the {@code trigger} property.
   * @see #trigger
   */
  public void setTrigger(BTimeTrigger v) { set(trigger, v, null); }

  //endregion Property "trigger"

  //region Action "generateChecksum"

  /**
   * Slot for the {@code generateChecksum} action.
   * @see #generateChecksum()
   */
  public static final Action generateChecksum = newAction(Flags.HIDDEN, null);

  /**
   * Invoke the {@code generateChecksum} action.
   * @see #generateChecksum
   */
  public void generateChecksum() { invoke(generateChecksum, null, null); }

  //endregion Action "generateChecksum"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BChecksumService.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
