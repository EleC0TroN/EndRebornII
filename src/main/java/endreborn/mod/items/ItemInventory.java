package endreborn.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemInventory extends InventoryBasic {

    private ItemStack    item;
    private EntityPlayer player;
    private boolean      reading = false;

    public ItemInventory(EntityPlayer player, ItemStack item, String name, boolean customName, int size) {

        super(name, customName, size);

        this.player = player;
        this.item = item;

        if (!hasInventory()) {
            createInventory();
        }

        loadInventory();
    }

    public static ItemInventory getItemInventory(ItemStack is, String name, int size) {

        return getItemInventory(null, is, name, size);
    }

    public static ItemInventory getItemInventory(EntityPlayer player, ItemStack is, String name, int size) {

        return new ItemInventory(player, is, name, false, size);
    }

    public ItemStack getItem() {

        return item;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        loadInventory();
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        super.closeInventory(player);
    }


    public void closeInventory(ItemStack is) {
        saveInventory(is);
    }

    private boolean hasInventory() {

        if (item.getTagCompound() == null) { return false; }
        return item.getTagCompound().getTag("Inventory") != null;
    }

    private void createInventory() {

        writeToNBT();
    }

    protected void writeToNBT() {

        if (item.getTagCompound() == null) {
            item.setTagCompound(new NBTTagCompound());
        }
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); i++) {
            if (!getStackInSlot(i).isEmpty()) {
                NBTTagCompound slotEntry = new NBTTagCompound();
                slotEntry.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(slotEntry);
                itemList.appendTag(slotEntry);
            }
        }
        NBTTagCompound inventory = new NBTTagCompound();
        inventory.setTag("Items", itemList);
        item.getTagCompound().setTag("Inventory", inventory);
    }

    public void loadInventory() {

        readFromNBT();
    }

    @Override
    public void markDirty() {

        super.markDirty();

        if (!reading) {
            saveInventory(ItemStack.EMPTY);
        }
    }

    protected void setNBT(ItemStack is) {

        if (is.isEmpty() && player != null) {
            is = player.getHeldItemMainhand();
        }

        if (!is.isEmpty() && is.getItem() == this.item.getItem()) {
            is.setTagCompound(item.getTagCompound());
        }
    }

    protected void readFromNBT() {

        reading = true;

        NBTTagList itemList = (NBTTagList) ((NBTTagCompound) item.getTagCompound().getTag("Inventory")).getTag("Items");
        for (int i = 0; i < itemList.tagCount(); i++) {
            NBTTagCompound slotEntry = itemList.getCompoundTagAt(i);
            int j = slotEntry.getByte("Slot") & 0xff;

            if (j >= 0 && j < getSizeInventory()) {
                setInventorySlotContents(j, new ItemStack(slotEntry));
            }
        }
        reading = false;
    }

    public void saveInventory(ItemStack is) {

        writeToNBT();
        setNBT(is);
    }
}
