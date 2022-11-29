package dev.alexnijjar.extractinator.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.alexnijjar.extractinator.Extractinator;
import dev.alexnijjar.extractinator.recipes.ExtractinatorRecipe;
import dev.alexnijjar.extractinator.registry.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class ExtractinatorCategory extends BaseCategory<ExtractinatorRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(Extractinator.MOD_ID, "extractinating");
    public static final RecipeType<ExtractinatorRecipe> RECIPE = new RecipeType<>(ID, ExtractinatorRecipe.class);
    private final IDrawable slot;

    public ExtractinatorCategory(IGuiHelper guiHelper) {
        super(guiHelper,
                RECIPE,
                new TranslatableComponent(ModItems.EXTRACTINATOR.get().getDescriptionId()),
                guiHelper.createBlankDrawable(144, 126),
                guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ModItems.EXTRACTINATOR.get().getDefaultInstance())
        );
        slot = guiHelper.getSlotDrawable();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtractinatorRecipe recipe, IFocusGroup focuses) {
        builder.addInvisibleIngredients(RecipeIngredientRole.CATALYST).addIngredients(Ingredient.of(ModItems.EXTRACTINATOR.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 1).addIngredients(recipe.input());
        List<Ingredient> outputs = recipe.getOutputs();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                int index = 8 * j + i;
                if (outputs.size() > index) {
                    builder.addSlot(RecipeIngredientRole.OUTPUT, i * 18 + 1, j * 18 + 37).addIngredients(outputs.get(index));
                }
            }
        }
    }

    @Override
    public void draw(ExtractinatorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        slot.draw(poseStack, 63, 0);
        Font font = Minecraft.getInstance().font;
        Component dropChanceText = new TranslatableComponent("text.extractinator.drop_chance", recipe.dropChance() * 100);
        font.draw(poseStack, dropChanceText, 70 - font.width(dropChanceText) / 2f, 22, 0x404040);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                slot.draw(poseStack, i * 18, j * 18 + 36);
            }
        }
    }
}
