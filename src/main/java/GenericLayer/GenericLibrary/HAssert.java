package GenericLayer.GenericLibrary;

import org.testng.Assert;
import org.testng.collections.Lists;
import org.testng.internal.EclipseInterface;

import java.lang.reflect.Array;
import java.util.*;

/*This is a copy of and can be used as a replacement of org.testng.Assert
 * The fail method is reimplemented with customization required by the framework to provide
 * any additional support required on test failure
 **/
public class HAssert extends Assert {

    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        UtilityMethods.takeScreenshot(message);
        throw new AssertionError(message);
    }

    public static void fail() {
        fail(null);
    }

    public static void assertEquals(Object var0, Object var1, String var2) {
        if (var1 != null && var1.getClass().isArray()) {
            assertArrayEquals(var0, var1, var2);
        } else {
            assertEqualsImpl(var0, var1, var2);
        }
    }

    private static void assertEqualsImpl(Object var0, Object var1, String var2) {
        if (var1 != null || var0 != null) {
            if (var1 == null ^ var0 == null) {
                failNotEquals(var0, var1, var2);
            }

            if (!var1.equals(var0) || !var0.equals(var1)) {
                failNotEquals(var0, var1, var2);
            }
        }
    }

    private static void assertArrayEquals(Object var0, Object var1, String var2) {
        if (var1 != var0) {
            if (null == var1) {
                fail("expected a null array, but not null found. " + var2);
            }

            if (null == var0) {
                fail("expected not null array, but null found. " + var2);
            }

            if (!var0.getClass().isArray()) {
                failNotEquals(var0, var1, var2);
            }

            int var3 = Array.getLength(var1);
            if (var3 != Array.getLength(var0)) {
                failNotEquals(Array.getLength(var0), var3, var2 == null ? "" : var2 + " (Array lengths are not the same)");
            }

            for (int var4 = 0; var4 < var3; ++var4) {
                Object var5 = Array.get(var0, var4);
                Object var6 = Array.get(var1, var4);

                try {
                    assertEquals(var5, var6);
                } catch (AssertionError var8) {
                    failNotEquals(var0, var1, var2 == null ? "" : var2 + " (values at index " + var4 + " are not the same)");
                }
            }

        }
    }

    public static void assertEquals(byte[] var0, byte[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(byte[] var0, byte[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Byte.toString(var1[var3]), Byte.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(short[] var0, short[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(short[] var0, short[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Short.toString(var1[var3]), Short.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(int[] var0, int[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(int[] var0, int[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Integer.toString(var1[var3]), Integer.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(boolean[] var0, boolean[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(boolean[] var0, boolean[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Boolean.toString(var1[var3]), Boolean.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(char[] var0, char[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(char[] var0, char[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Character.toString(var1[var3]), Character.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(float[] var0, float[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(float[] var0, float[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Float.toString(var1[var3]), Float.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(double[] var0, double[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(double[] var0, double[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Double.toString(var1[var3]), Double.toString(var0[var3]), var2));
                }
            }

        }
    }

    public static void assertEquals(long[] var0, long[] var1) {
        assertEquals(var0, var1, "");
    }

    public static void assertEquals(long[] var0, long[] var1, String var2) {
        if (!checkRefEqualityAndLength(var0, var1, var2)) {
            for (int var3 = 0; var3 < var1.length; ++var3) {
                if (var1[var3] != var0[var3]) {
                    fail(String.format("arrays differ firstly at element [%d]; expected value is <%s> but was <%s>. %s", var3, Long.toString(var1[var3]), Long.toString(var0[var3]), var2));
                }
            }

        }
    }

    private static boolean checkRefEqualityAndLength(Object var0, Object var1, String var2) {
        if (var1 == var0) {
            return true;
        } else {
            if (null == var1) {
                fail("expectedArray a null array, but not null found. " + var2);
            }

            if (null == var0) {
                fail("expectedArray not null array, but null found. " + var2);
            }

            assertEquals(Array.getLength(var0), Array.getLength(var1), "arrays don't have the same size. " + var2);
            return false;
        }
    }

    public static void assertEquals(Object var0, Object var1) {
        assertEquals((Object) var0, (Object) var1, (String) null);
    }

    public static void assertEquals(String var0, String var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(String var0, String var1) {
        assertEquals((String) var0, (String) var1, (String) null);
    }

    public static void assertEquals(double var0, double var2, double var4, String var6) {
        if (Double.isInfinite(var2)) {
            if (var2 != var0) {
                failNotEquals(var0, var2, var6);
            }
        } else if (Double.isNaN(var2)) {
            if (!Double.isNaN(var0)) {
                failNotEquals(var0, var2, var6);
            }
        } else if (Math.abs(var2 - var0) > var4) {
            failNotEquals(var0, var2, var6);
        }

    }

    public static void assertEquals(double var0, double var2, double var4) {
        assertEquals(var0, var2, var4, (String) null);
    }

    public static void assertEquals(float var0, float var1, float var2, String var3) {
        if (Float.isInfinite(var1)) {
            if (var1 != var0) {
                failNotEquals(var0, var1, var3);
            }
        } else if (Math.abs(var1 - var0) > var2) {
            failNotEquals(var0, var1, var3);
        }

    }

    public static void assertEquals(float var0, float var1, float var2) {
        assertEquals(var0, var1, var2, (String) null);
    }

    public static void assertEquals(long var0, long var2, String var4) {
        assertEquals((Object) var0, (Object) var2, var4);
    }

    public static void assertEquals(long var0, long var2) {
        assertEquals(var0, var2, (String) null);
    }

    public static void assertEquals(boolean var0, boolean var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(boolean var0, boolean var1) {
        assertEquals(var0, var1, (String) null);
    }

    public static void assertEquals(byte var0, byte var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(byte var0, byte var1) {
        assertEquals((byte) var0, (byte) var1, (String) null);
    }

    public static void assertEquals(char var0, char var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(char var0, char var1) {
        assertEquals((char) var0, (char) var1, (String) null);
    }

    public static void assertEquals(short var0, short var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(short var0, short var1) {
        assertEquals((short) var0, (short) var1, (String) null);
    }

    public static void assertEquals(int var0, int var1, String var2) {
        assertEquals((Object) var0, (Object) var1, var2);
    }

    public static void assertEquals(int var0, int var1) {
        assertEquals((int) var0, (int) var1, (String) null);
    }

    public static void assertNotNull(Object var0) {
        assertNotNull(var0, (String) null);
    }

    public static void assertNotNull(Object var0, String var1) {
        if (var0 == null) {
            String var2 = "";
            if (var1 != null) {
                var2 = var1 + " ";
            }

            fail(var2 + "expected object to not be null");
        }

        assertTrue(var0 != null, var1);
    }

    public static void assertTrue(boolean var0, String var1) {
        if (!var0) {
            failNotEquals(var0, Boolean.TRUE, var1);
        }

    }

    public static void assertTrue(boolean var0) {
        assertTrue(var0, (String) null);
    }

    public static void assertFalse(boolean var0, String var1) {
        if (var0) {
            failNotEquals(var0, Boolean.FALSE, var1);
        }

    }

    public static void assertNull(Object var0) {
        assertNull(var0, (String) null);
    }

    public static void assertNull(Object var0, String var1) {
        if (var0 != null) {
            failNotSame(var0, (Object) null, var1);
        }

    }

    public static void assertSame(Object var0, Object var1, String var2) {
        if (var1 != var0) {
            failNotSame(var0, var1, var2);
        }
    }

    public static void assertSame(Object var0, Object var1) {
        assertSame(var0, var1, (String) null);
    }

    public static void assertNotSame(Object var0, Object var1, String var2) {
        if (var1 == var0) {
            failSame(var0, var1, var2);
        }

    }

    public static void assertNotSame(Object var0, Object var1) {
        assertNotSame(var0, var1, (String) null);
    }

    private static void failSame(Object var0, Object var1, String var2) {
        String var3 = "";
        if (var2 != null) {
            var3 = var2 + " ";
        }

        fail(var3 + EclipseInterface.ASSERT_LEFT2 + var1 + EclipseInterface.ASSERT_MIDDLE + var0 + EclipseInterface.ASSERT_RIGHT);
    }

    private static void failNotSame(Object var0, Object var1, String var2) {
        String var3 = "";
        if (var2 != null) {
            var3 = var2 + " ";
        }

        fail(var3 + EclipseInterface.ASSERT_LEFT + var1 + EclipseInterface.ASSERT_MIDDLE + var0 + EclipseInterface.ASSERT_RIGHT);
    }

    private static void failNotEquals(Object var0, Object var1, String var2) {
        fail(format(var0, var1, var2));
    }

    static String format(Object var0, Object var1, String var2) {
        String var3 = "";
        if (null != var2) {
            var3 = var2 + " ";
        }

        return var3 + EclipseInterface.ASSERT_LEFT + var1 + EclipseInterface.ASSERT_MIDDLE + var0 + EclipseInterface.ASSERT_RIGHT;
    }

    public static void assertEquals(Collection<?> var0, Collection<?> var1) {
        assertEquals((Collection) var0, (Collection) var1, (String) null);
    }

    public static void assertEquals(Collection<?> var0, Collection<?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                if (var2 != null) {
                    fail(var2);
                } else {
                    fail("Collections not equal: expected: " + var1 + " and actual: " + var0);
                }
            }

            assertEquals(var0.size(), var1.size(), (var2 == null ? "" : var2 + ": ") + "lists don't have the same size");
            Iterator var3 = var0.iterator();
            Iterator var4 = var1.iterator();
            int var5 = -1;

            while (var3.hasNext() && var4.hasNext()) {
                ++var5;
                Object var6 = var4.next();
                Object var7 = var3.next();
                String var8 = "Lists differ at element [" + var5 + "]: " + var6 + " != " + var7;
                String var9 = var2 == null ? var8 : var2 + ": " + var8;
                assertEqualsImpl(var7, var6, var9);
            }

        }
    }

    public static void assertEquals(Iterator<?> var0, Iterator<?> var1) {
        assertEquals((Iterator) var0, (Iterator) var1, (String) null);
    }

    public static void assertEquals(Iterator<?> var0, Iterator<?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                String var3 = var2 != null ? var2 : "Iterators not equal: expected: " + var1 + " and actual: " + var0;
                fail(var3);
            }

            int var8 = -1;

            while (var0.hasNext() && var1.hasNext()) {
                ++var8;
                Object var4 = var1.next();
                Object var5 = var0.next();
                String var6 = "Iterators differ at element [" + var8 + "]: " + var4 + " != " + var5;
                String var7 = var2 == null ? var6 : var2 + ": " + var6;
                assertEqualsImpl(var5, var4, var7);
            }

            String var9;
            String var10;
            if (var0.hasNext()) {
                var9 = "Actual iterator returned more elements than the expected iterator.";
                var10 = var2 == null ? var9 : var2 + ": " + var9;
                fail(var10);
            } else if (var1.hasNext()) {
                var9 = "Expected iterator returned more elements than the actual iterator.";
                var10 = var2 == null ? var9 : var2 + ": " + var9;
                fail(var10);
            }

        }
    }

    public static void assertEquals(Iterable<?> var0, Iterable<?> var1) {
        assertEquals((Iterable) var0, (Iterable) var1, (String) null);
    }

    public static void assertEquals(Iterable<?> var0, Iterable<?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                if (var2 != null) {
                    fail(var2);
                } else {
                    fail("Iterables not equal: expected: " + var1 + " and actual: " + var0);
                }
            }

            Iterator var3 = var0.iterator();
            Iterator var4 = var1.iterator();
            assertEquals(var3, var4, var2);
        }
    }

    public static void assertEquals(Object[] var0, Object[] var1, String var2) {
        if (var0 != var1) {
            if (var0 == null && var1 != null || var0 != null && var1 == null) {
                if (var2 != null) {
                    fail(var2);
                } else {
                    fail("Arrays not equal: " + Arrays.toString(var1) + " and " + Arrays.toString(var0));
                }
            }

            assertEquals((Collection) Arrays.asList(var0), (Collection) Arrays.asList(var1), var2);
        }
    }

    public static void assertEqualsNoOrder(Object[] var0, Object[] var1, String var2) {
        if (var0 != var1) {
            if (var0 == null && var1 != null || var0 != null && var1 == null) {
                failAssertNoEqual("Arrays not equal: " + Arrays.toString(var1) + " and " + Arrays.toString(var0), var2);
            }

            if (var0.length != var1.length) {
                failAssertNoEqual("Arrays do not have the same size:" + var0.length + " != " + var1.length, var2);
            }

            List var3 = Lists.newArrayList();
            Object[] var4 = var0;
            int var5 = var0.length;

            int var6;
            Object var7;
            for (var6 = 0; var6 < var5; ++var6) {
                var7 = var4[var6];
                var3.add(var7);
            }

            var4 = var1;
            var5 = var1.length;

            for (var6 = 0; var6 < var5; ++var6) {
                var7 = var4[var6];
                var3.remove(var7);
            }

            if (var3.size() != 0) {
                failAssertNoEqual("Arrays not equal: " + Arrays.toString(var1) + " and " + Arrays.toString(var0), var2);
            }

        }
    }

    private static void failAssertNoEqual(String var0, String var1) {
        if (var1 != null) {
            fail(var1);
        } else {
            fail(var0);
        }

    }

    public static void assertEquals(Object[] var0, Object[] var1) {
        assertEquals((Object[]) var0, (Object[]) var1, (String) null);
    }

    public static void assertEqualsNoOrder(Object[] var0, Object[] var1) {
        assertEqualsNoOrder(var0, var1, (String) null);
    }

    public static void assertEquals(Set<?> var0, Set<?> var1) {
        assertEquals((Set) var0, (Set) var1, (String) null);
    }

    public static void assertEquals(Set<?> var0, Set<?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                if (var2 == null) {
                    fail("Sets not equal: expected: " + var1 + " and actual: " + var0);
                } else {
                    failNotEquals(var0, var1, var2);
                }
            }

            if (!var0.equals(var1)) {
                if (var2 == null) {
                    fail("Sets differ: expected " + var1 + " but got " + var0);
                } else {
                    failNotEquals(var0, var1, var2);
                }
            }

        }
    }

    public static void assertEqualsDeep(Set<?> var0, Set<?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                if (var2 == null) {
                    fail("Sets not equal: expected: " + var1 + " and actual: " + var0);
                } else {
                    failNotEquals(var0, var1, var2);
                }
            }

            Iterator var3 = var0.iterator();
            Iterator var4 = var1.iterator();

            while (var4.hasNext()) {
                Object var5 = var4.next();
                if (!var3.hasNext()) {
                    fail("Sets not equal: expected: " + var1 + " and actual: " + var0);
                }

                Object var6 = var3.next();
                if (var5.getClass().isArray()) {
                    assertArrayEquals(var6, var5, var2);
                } else {
                    assertEqualsImpl(var6, var5, var2);
                }
            }

        }
    }

    public static void assertEquals(Map<?, ?> var0, Map<?, ?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                fail("Maps not equal: expected: " + var1 + " and actual: " + var0);
            }

            if (var0.size() != var1.size()) {
                fail("Maps do not have the same size:" + var0.size() + " != " + var1.size());
            }

            Set var3 = var0.entrySet();
            Iterator var4 = var3.iterator();

            while (var4.hasNext()) {
                Object var5 = var4.next();
                Map.Entry var6 = (Map.Entry) var5;
                Object var7 = var6.getKey();
                Object var8 = var6.getValue();
                Object var9 = var1.get(var7);
                String var10 = var2 != null ? var2 : "Maps do not match for key:" + var7 + " actual:" + var8 + " expected:" + var9;
                assertEqualsImpl(var8, var9, var10);
            }

        }
    }

    public static void assertEqualsDeep(Map<?, ?> var0, Map<?, ?> var1) {
        assertEqualsDeep((Map) var0, (Map) var1, (String) null);
    }

    public static void assertEqualsDeep(Map<?, ?> var0, Map<?, ?> var1, String var2) {
        if (var0 != var1) {
            if (var0 == null || var1 == null) {
                fail("Maps not equal: expected: " + var1 + " and actual: " + var0);
            }

            if (var0.size() != var1.size()) {
                fail("Maps do not have the same size:" + var0.size() + " != " + var1.size());
            }

            Set var3 = var0.entrySet();
            Iterator var4 = var3.iterator();

            while (var4.hasNext()) {
                Object var5 = var4.next();
                Map.Entry var6 = (Map.Entry) var5;
                Object var7 = var6.getKey();
                Object var8 = var6.getValue();
                Object var9 = var1.get(var7);
                String var10 = var2 != null ? var2 : "Maps do not match for key:" + var7 + " actual:" + var8 + " expected:" + var9;
                if (var9.getClass().isArray()) {
                    assertArrayEquals(var8, var9, var10);
                } else {
                    assertEqualsImpl(var8, var9, var10);
                }
            }

        }
    }

    public static void assertNotEquals(Object var0, Object var1, String var2) {
        boolean var3;
        try {
            assertEquals(var0, var1);
            var3 = true;
        } catch (AssertionError var5) {
            var3 = false;
        }

        if (var3) {
            fail(var2);
        }

    }

    public static void assertNotEquals(Object var0, Object var1) {
        assertNotEquals((Object) var0, (Object) var1, (String) null);
    }

    static void assertNotEquals(String var0, String var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(String var0, String var1) {
        assertNotEquals((String) var0, (String) var1, (String) null);
    }

    static void assertNotEquals(long var0, long var2, String var4) {
        assertNotEquals((Object) var0, (Object) var2, var4);
    }

    static void assertNotEquals(long var0, long var2) {
        assertNotEquals(var0, var2, (String) null);
    }

    static void assertNotEquals(boolean var0, boolean var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(boolean var0, boolean var1) {
        assertNotEquals(var0, var1, (String) null);
    }

    static void assertNotEquals(byte var0, byte var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(byte var0, byte var1) {
        assertNotEquals((byte) var0, (byte) var1, (String) null);
    }

    static void assertNotEquals(char var0, char var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(char var0, char var1) {
        assertNotEquals((char) var0, (char) var1, (String) null);
    }

    static void assertNotEquals(short var0, short var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(short var0, short var1) {
        assertNotEquals((short) var0, (short) var1, (String) null);
    }

    static void assertNotEquals(int var0, int var1, String var2) {
        assertNotEquals((Object) var0, (Object) var1, var2);
    }

    static void assertNotEquals(int var0, int var1) {
        assertNotEquals((int) var0, (int) var1, (String) null);
    }

    public static void assertNotEquals(float var0, float var1, float var2, String var3) {
        boolean var4;
        try {
            assertEquals(var0, var1, var2, var3);
            var4 = true;
        } catch (AssertionError var6) {
            var4 = false;
        }

        if (var4) {
            fail(var3);
        }

    }

    public static void assertNotEquals(float var0, float var1, float var2) {
        assertNotEquals(var0, var1, var2, (String) null);
    }

    public static void assertNotEquals(double var0, double var2, double var4, String var6) {
        boolean var7;
        try {
            assertEquals(var0, var2, var4, var6);
            var7 = true;
        } catch (AssertionError var9) {
            var7 = false;
        }

        if (var7) {
            fail(var6);
        }

    }

    public static void assertNotEquals(Set<?> var0, Set<?> var1) {
        assertNotEquals((Set) var0, (Set) var1, (String) null);
    }

    public static void assertNotEquals(Set<?> var0, Set<?> var1, String var2) {
        boolean var3;
        try {
            assertEquals(var0, var1, var2);
            var3 = true;
        } catch (AssertionError var5) {
            var3 = false;
        }

        if (var3) {
            fail(var2);
        }

    }

    public static void assertNotEqualsDeep(Set<?> var0, Set<?> var1) {
        assertNotEqualsDeep((Set) var0, (Set) var1, (String) null);
    }

    public static void assertNotEqualsDeep(Set<?> var0, Set<?> var1, String var2) {
        boolean var3;
        try {
            assertEqualsDeep(var0, var1, var2);
            var3 = true;
        } catch (AssertionError var5) {
            var3 = false;
        }

        if (var3) {
            fail(var2);
        }

    }

    public static void assertNotEquals(Map<?, ?> var0, Map<?, ?> var1) {
        assertNotEquals((Map) var0, (Map) var1, (String) null);
    }

    public static void assertNotEquals(Map<?, ?> var0, Map<?, ?> var1, String var2) {
        boolean var3;
        try {
            assertEquals(var0, var1, var2);
            var3 = true;
        } catch (AssertionError var5) {
            var3 = false;
        }

        if (var3) {
            fail(var2);
        }

    }

    public static void assertNotEqualsDeep(Map<?, ?> var0, Map<?, ?> var1) {
        assertNotEqualsDeep((Map) var0, (Map) var1, (String) null);
    }

    public static void assertNotEqualsDeep(Map<?, ?> var0, Map<?, ?> var1, String var2) {
        boolean var3;
        try {
            assertEqualsDeep(var0, var1, var2);
            var3 = true;
        } catch (AssertionError var5) {
            var3 = false;
        }

        if (var3) {
            fail(var2);
        }

    }

    public static void assertNotEquals(double var0, double var2, double var4) {
        assertNotEquals(var0, var2, var4, (String) null);
    }

    public interface ThrowingRunnable {
        void run() throws Throwable;
    }
}
