// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @author
 */
class Machine {
    private Rotor[] rotors;
    private boolean cause;
    // This needs other methods or constructors.
    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'. */
    void replaceRotors(Rotor[] input) {
        // FIXME
        this.rotors = new Rotor[]{input[0], input[1],
                input[2], input[3], input[4]};
        for (int i = 0; i < this.rotors.length; i++) {
            rotors[i].set(0);
        }
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        // FIXME
        String[] set = setting.split("");
        int i = 1;
        for (; i < this.rotors.length; i++) {
            rotors[i].set((int) (set[i - 1].charAt(0) - 'A'));
        }
    }
    void setStatus() {
        this.rotors[4].advance();
        if (this.rotors[4].getNotch().indexOf(Rotor.toLetter(this.rotors[4].getSetting() - 1))
                != -1) {
            this.rotors[3].advance();
            if (this.rotors[3].getNotch().indexOf(Rotor.toLetter(this.rotors[3].getSetting() - 1))
                    != -1) {
                this.rotors[2].advance();
                cause = true;
                //this.rotors[3].advance();
            }
        } else { //situation where 3 not over but should be over.
            if ((this.rotors[3].getNotch().indexOf(Rotor.toLetter(this.rotors[3].getSetting()))
                    != -1)
                    && !cause) {
                this.rotors[2].advance();
                this.rotors[3].advance();
            }
            cause = false;
        }
    }
    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        char c;
        String string = new String();
        for (int i = 0; i < msg.length(); i++) {
            setStatus();
            c = msg.charAt(i);
            for (int j = 4; j >= 0; j--) {
                c = Rotor.toLetter(this.rotors[j].convertForward(Rotor.toIndex(c)));
            }
            for (int j = 1; j <= 4; j++) {
                c = Rotor.toLetter(this.rotors[j].convertBackward(Rotor.toIndex(c)));
            }
            string += c;
        }
        return string;

        // FIXME
    }
}
