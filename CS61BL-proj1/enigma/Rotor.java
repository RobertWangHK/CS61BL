// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author
 */
class Rotor {
    // This needs other methods, fields, and constructors.

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;
    private int _setting;
    private String index;
    private PermutationData permute;
    private String notch;
    private String[] _transformtable;
    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        if (p >= 0 && p < 25) {
            return (char) (p + 65);
        } else if (p < 0) {
            for (int i = 0; i < 5; i++) {
                if (p + i * 26 >= 0) {
                    return (char) (p + i * 26 + 65);
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (p - i * 26 <= 25) {
                    return (char) (p - i * 26 + 65);
                }
            }
        }
        return 0;  // FIXME
    }

    public String getNotch() {
        return notch;
    }

    public String[] getTransformtable() {
        return _transformtable;
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        int result;
        if ((c - 'A') >= 0 && (c - 'A') <= 25) {
            return (int) (c - 'A');
        } else if ((c - 'A') < 0) {
            for (int i = 0; i <= 5; i++) {
                if ((int) (c - 'A') + i * 26 >= 0) {
                    return  (int) (c - 'A' + i * 26);
                }
            }
        } else {
            for (int i = 0; i <= 5; i++) {
                if ((int) (c - 'A') - i * 26 <= 25) {
                    return (int) (c - 'A' - i * 26);
                }
            }
        }
        return 0;  // FIXME
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }
    void setIndex(String index) {
        this.index = index;
        permute = new PermutationData();
        _transformtable = new String[2];
        boolean contains = false;
        for (int i = 0; i < PermutationData.ROTOR_SPECS.length; i++) {
            if (PermutationData.ROTOR_SPECS[i][0].equals(index)) {
                contains = true;
                if (this.atNotch()) {
                    if (this.index.equals("BETA") || this.index.equals("GAMMA")
                            || this.index.equals("B") || this.index.equals("C")) {
                        throw new EnigmaException("bad config");
                    }
                    this.notch = PermutationData.ROTOR_SPECS[i][3];
                }
                this._transformtable[0] = PermutationData.ROTOR_SPECS[i][1];
                if (this.hasInverse()) {
                    if (this.index.equals("B") || this.index.equals("C")) {
                        throw new EnigmaException("bad config");
                    }
                    this._transformtable[1] = PermutationData.ROTOR_SPECS[i][2];
                }
                break;
            }
        }
        if (!contains) {
            throw  new EnigmaException("Misnamed rotors");
        }
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        char temp;
        int result;
        temp = match(Rotor.toLetter(this._setting + p));
        result = Rotor.toIndex(temp) - this._setting;
        if (result < 0) {
            result += 26;
        }
        return result; // FIXME
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        char temp;
        int result;
        temp = inverseMatch(Rotor.toLetter(this._setting + e));
        result = Rotor.toIndex(temp) - this._setting;
        if (result < 0) {
            result += 26;
        }
        return result; // FIXME
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        return true; // FIXME
    }

    /** Advance me one position. */
    void advance() {
        this._setting = (this._setting + 1) % 26;
        // FIXME
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    char match(char p) {
        char temp = 'A';
        int aa = p - 'A';
        temp = this._transformtable[0].charAt(aa);
        return temp;
    }
    char inverseMatch(char p) {
        char temp = 'A';
        int aa = p - 'A';
        temp = this._transformtable[1].charAt(aa);
        return temp;
    }
}
