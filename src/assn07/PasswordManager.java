package assn07;

import java.util.*;

import static java.lang.Math.abs;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "xyz"; // You can change this
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    private int findIndex(K key) {
        // Turn the K key into an integer 0-49 (inclusive) which will be the index we will put new entries into
        int index = abs(key.hashCode()) % _passwords.length;
        return index;
    }

    // TODO: put
    @Override
    public void put(K key, V value) {
        int index = findIndex(key);
        Account<K, V> initial = _passwords[index];

        if (initial == null) {
            _passwords[index] = new Account<>(key, value);
            return;
        } else {
            Account<K, V> current = initial;
            while (true) {
                if (current.getWebsite().equals(key)) {
                    current.setPassword(value);
                    return;
                }
                if (current.getNext() == null) {
                    break;
                }
                current = current.getNext();
            }
            current.setNext(new Account<>(key, value));
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = findIndex(key);
        Account<K, V> current = _passwords[index];

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return current.getPassword();
            }
            current = current.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < _passwords.length; i++) {
            Account<K, V> current = _passwords[i];
            while(current != null) {
                count++;
                current = current.getNext();
            }
        }
        return count;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        // sets don't allow for repeats
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < _passwords.length; i++) {
            Account<K, V> current = _passwords[i];
            while (current != null) {
                keys.add(current.getWebsite());
                current = current.getNext();
            }
        }
       return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = findIndex(key);
        Account<K, V> current = _passwords[index];
        Account<K, V> previous = null;

        while (current != null) {
            if (current.getWebsite().equals(key)) {
                if (previous == null) {
                    _passwords[index] = current.getNext();
                    return current.getPassword();
                } else {
                    previous.setNext(current.getNext());
                    return current.getPassword();
                }
            }
            previous = current;
            current = current.getNext();
        }
       return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicates(V value) {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < _passwords.length; i++) {
            Account<K, V> current = _passwords[i];
            while (current != null) {
                if (current.getPassword().equals(value)) {
                    keys.add(current.getWebsite());
                }
                current = current.getNext();
            }
        }
        // Convert set to an ArrayList
        List<K> keysAL = new ArrayList<>(keys);
        return keysAL;
    }

    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    @Override
    public String generatesafeRandomPassword(int length) {
        // TODO:
        if (length < 4) {
            length = 4;
        }
        
        int leftLimit = 48; // hint: numeral '0'=48
        int rightLimit = 122; // hint: letter 'z'=122
        int targetStringLength = length;
        Random random = new Random();

        // TODO: Ensure the minimum length is 4


        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
