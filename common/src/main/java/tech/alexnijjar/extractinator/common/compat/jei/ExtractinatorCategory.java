package dev.alexnijjar.extractinator.common.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import tech.alexnijjar.extractinator.Extractinator;
import tech.alexnijjar.extractinator.common.compat.jei.BaseCategory;
import tech.alexnijjar.extractinator.common.recipe.ExtractinatorRecipe;
import tech.alexnijjar.extractinator.common.registry.ModItems;

import java.util.List;

public class ExtractinatorCategory extends BaseCategory<ExtractinatorRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(Extractinator.MOD_ID, "extractinating");
    public static final RecipeType<ExtractinatorRecipe> RECIPE = new RecipeType<>(ID, ExtractinatorRecipe.class);
    private final IDrawable slot;

    public ExtractinatorCategory(IGuiHelper guiHelper) {
        super(guiHelper,
            RECIPE,
            Component.translatable(ModItems.EXTRACTINATOR.get().getDescriptionId()),
            guiHelper.createBlankDrawable(144, 144),
            guiHelper.createDrawableItemStack(ModItems.EXTRACTINATOR.get().getDefaultInstance())
        );
        slot = guiHelper.getSlotDrawable();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtractinatorRecipe recipe, IFocusGroup focuses) {
        builder.addInvisibleIngredients(RecipeIngredientRole.CATALYST).addIngredients(Ingredient.of(ModItems.EXTRACTINATOR.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 64, 1).addIngredients(recipe.input());
        List<Ingredient> outputs = recipe.getOutputs();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                int index = 8 * j + i;
                if (outputs.size() > index) {
                    builder.addSlot(RecipeIngredientRole.OUTPUT, i * 18 + 1, j * 18 + 21).addIngredients(outputs.get(index));
                }
            }
        }
    }

    @Override
    public void draw(ExtractinatorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        slot.draw(poseStack, 63, 0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                slot.draw(poseStack, i * 18, j * 18 + 20);
            }
        }
    }
}