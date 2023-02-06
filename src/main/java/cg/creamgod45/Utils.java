package cg.creamgod45;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class Utils {

    public static Boolean HasPermission(Player player, String Permission) {
        if (Permission == null) {
            Permission = "CGItemLevelLimit.admin";
            return false;
        }
        if (player.hasPermission("CGItemLevelLimit.admin")) return true;
        if (player.isOp()) return true;

        return player.hasPermission(Permission);
    }

    public static String format(String string) {
        if (string == null)
            return string;
        else
            return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String decolor(String string) {
        return string.replaceAll("&1|&2|&3|&4|&5|&6|&7|&8|&9|&a|&b|&c|&d|&e|&f|&l|&m|&n|&o|&r", "");
    }

    public static ItemStack getCustomSkull(String url) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
    }

    public static List<String> getMaterialList() {
        List<String> enumNames = Stream.of(Material.values())
                .map(Enum::name).toList();
        List<String> enumNames1 = new ArrayList<>();
        for (String enumName : enumNames) {
            Material material = Material.matchMaterial(enumName, false);
            if (material == null) continue;
            if (material.isAir()) continue;
            if (!material.isItem()) continue; // 這個會過濾無法正常在介面中顯示的物品
            ItemStack build = new ItemBuilder(Material.getMaterial(enumName)).build();
            if (build == null) continue;
            enumNames1.add(enumName);
        }
        return enumNames1;
    }

    public static OfflinePlayer getPlayer(String player_name) {
        List<Player> player_list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player player : player_list) {
            Utils.ConsoleInfoPrint(String.valueOf(player.getName().equals(player_name)));
            Utils.ConsoleInfoPrint("c1=" + player.getName());
            Utils.ConsoleInfoPrint("c2=" + player_name);
            if (player.getName().equals(player_name)) {
                return player;
            }
        }

        return null;
    }

    public static OfflinePlayer getPlayer(UUID uuid) {
        List<Player> player_list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player player : player_list) {
            if (player.getUniqueId().equals(uuid)) {
                return player;
            }
        }

        return null;
    }


    public static int getPlayerNum() {
        int num = 0;
        List<Player> player_list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player player : player_list) {
            num++;
        }
        return num;
    }

    public static String arraytostring(String[] array, int start) {
        if (array == null) return "null";
        if (start < 0) return "null";
        array = Arrays.copyOfRange(array, start, array.length);
        return String.join(" ", array);
    }

    public static void removeOne(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i++) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                int amount = other.getAmount();
                if (amount > 1) {
                    other.setAmount(amount - 1);
                } else {
                    other.setAmount(other.getAmount() - 1);
                }
                inventory.setItem(i, other);
                break;
            }
        }
    }

    public static Boolean hasItem(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        for (int i = 0; i < size; i++) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean EnoughInventory(Player player) {
        int space = 0;
        for (ItemStack itemStack : player.getInventory()) {
            try {
                String s = itemStack.toString();
            } catch (NullPointerException e) {
                space += 1;
            }
        }
        try {
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
            if (itemInOffHand.getType() == Material.AIR) {
                space -= 1;
            }
        } catch (NullPointerException e) {
            space -= 1;
        }

        ItemStack[] armorContents = player.getInventory().getArmorContents();
        for (ItemStack armorContent : armorContents) {
            try {
                armorContent.toString();
            } catch (NullPointerException e) {
                space -= 1;
            }
        }
        return space > 0;
    }

    public static void ConsoleInfoPrint(String msg) {
        Script.logger.info(Utils.format(msg));
    }

    public static void ConsoleInfoPrint(String msg, boolean color) {
        if (color) {
            ConsoleInfoPrint(msg);
        } else {
            Script.logger.info(msg);
        }
    }

    public static void ConsoleInfoPrint(String msg, int space) {
        String spacer = "";
        if (space > 0) {
            for (int i = 0; i < space; i++) {
                spacer += " ";
            }

            ConsoleInfoPrint(spacer + Utils.format(msg));
        } else {
            ConsoleInfoPrint(Utils.format(msg));
        }
    }

    public static String getRandomString(int i) {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuilder thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring = new String(bytearray, StandardCharsets.UTF_8);

        thebuffer = new StringBuilder();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }

    public static void setMetadata(Entity player, String key, Object value, Plugin plugin) {
        player.setMetadata(key, new FixedMetadataValue(plugin, value));
    }
}
