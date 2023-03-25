package dev.idw0309.joinleave.fireworks;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Color {

    public static org.bukkit.Color parseColor(String s) {

        org.bukkit.Color color = null;
        String[] split = s.split(" ");
        if (split.length > 2) {
            try {
                // RGB
                int red = Integer.parseInt(split[0]);
                int green = Integer.parseInt(split[1]);
                int blue = Integer.parseInt(split[2]);
                color = org.bukkit.Color.fromRGB(red, green, blue);

            } catch (NumberFormatException e) {
                // Name
                Field[] fields = org.bukkit.Color.class.getFields();
                for (Field field : fields) {
                    if (Modifier.isStatic(field.getModifiers())
                            && field.getType() == org.bukkit.Color.class) {

                        if (field.getName().equalsIgnoreCase(s)) {
                            try {
                                return (org.bukkit.Color) field.get(null);
                            } catch (IllegalArgumentException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                }

            }
        } else {
            // Name
            Field[] fields = org.bukkit.Color.class.getFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())
                        && field.getType() == org.bukkit.Color.class) {

                    if (field.getName().equalsIgnoreCase(s)) {
                        try {
                            return (org.bukkit.Color) field.get(null);
                        } catch (IllegalArgumentException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            }

        }

        return color;
    }

}
