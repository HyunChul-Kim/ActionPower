package com.example.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.designsystem.component.FavoriteToggleButton
import com.example.model.DrinkDetailResource
import com.example.model.FavoriteData
import com.example.model.Language

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState.Loading,
    onClickFavorite: (DrinkDetailResource, Boolean) -> Unit,
) {

    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        when (detailUiState) {
            is DetailUiState.Success -> {
                DetailResultColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    favoriteDrinkDetailData = detailUiState.favoriteDrinkDetailData,
                    onClickFavorite = onClickFavorite
                )
            }

            is DetailUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is DetailUiState.Error -> {

            }
        }
    }
}

@Composable
private fun DetailResultColumn(
    modifier: Modifier = Modifier,
    favoriteDrinkDetailData: FavoriteData<DrinkDetailResource>,
    onClickFavorite: (DrinkDetailResource, Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = favoriteDrinkDetailData.data.thumbnail,
            contentDescription = "detail image",
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MainTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    title = favoriteDrinkDetailData.data.name
                )
                FavoriteToggleButton(
                    isFavorite = favoriteDrinkDetailData.isFavorite,
                    onCheckedChange = { checked ->
                        onClickFavorite(favoriteDrinkDetailData.data, checked)
                    }
                )
            }
            SubContent(
                title = stringResource(
                    id = R.string.feature_detail_instructions_title
                ),
                content = favoriteDrinkDetailData.data.instructionsToString
            )
            SubContent(
                title = stringResource(
                    id = R.string.feature_detail_ingredients_title
                ),
                content = favoriteDrinkDetailData.data.ingredientsToString
            )
            SubContent(
                title = stringResource(
                    id = R.string.feature_detail_iba_title
                ),
                content = favoriteDrinkDetailData.data.iba
            )
            SubContent(
                title = stringResource(
                    id = R.string.feature_detail_category_title
                ),
                content = favoriteDrinkDetailData.data.category
            )
            ModifiedDate(modifiedDate = favoriteDrinkDetailData.data.dateModified)
        }
    }
}

@Composable
private fun MainTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 35.sp
    )
}

@Composable
private fun SubTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun SubContent(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    if(content.isEmpty()) return
    Column(
        modifier = modifier
    ) {
        SubTitle(title = title)
        Text(text = content)
    }
}

@Composable
private fun ModifiedDate(
    modifiedDate: String
) {
    if(modifiedDate.isEmpty()) return
    Text(
        text = stringResource(
            id = R.string.feature_detail_modified_date,
            modifiedDate
        )
    )
}

@Preview
@Composable
private fun DetailResultColumnPreview() {
    val instructions = mapOf(
        Language.Default.value to "Rub the rim of the glass with the lime slice to make the salt stick to it. " +
                "Take care to moisten only the outer rim and sprinkle the salt on it. The salt should " +
                "present to the lips of the imbiber and never mix into the cocktail. Shake the other " +
                "ingredients with ice, then carefully pour into the glass.",
        Language.DE.value to "Reiben Sie den Rand des Glases mit der Limettenscheibe, damit das Salz daran " +
                "haftet. Achten Sie darauf, dass nur der \\u00e4u\\u00dfere Rand angefeuchtet wird und " +
                "streuen Sie das Salz darauf. Das Salz sollte sich auf den Lippen des Genie\\u00dfers " +
                "befinden und niemals in den Cocktail einmischen. Die anderen Zutaten mit Eis " +
                "sch\\u00fctteln und vorsichtig in das Glas geben.",
        Language.IT.value to "Strofina il bordo del bicchiere con la fetta di lime per far aderire il sale." +
                "\r\nAvere cura di inumidire solo il bordo esterno e cospargere di sale.\r\nIl sale " +
                "dovrebbe presentarsi alle labbra del bevitore e non mescolarsi mai al cocktail." +
                "\r\nShakerare gli altri ingredienti con ghiaccio, quindi versarli delicatamente nel " +
                "bicchiere."
    )
    val ingredients = listOf(
        "Tequila", "Triple sec", "Lime juice"
    )
    DetailResultColumn(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White),
        favoriteDrinkDetailData = FavoriteData(
            isFavorite = true,
            data = DrinkDetailResource(
                name = "MargaritaMargarita",
                category = "Ordinary Drink",
                iba = "Contemporary Classics",
                instructions = instructions,
                ingredients = ingredients,
                id = "11007",
                thumbnail = "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
                dateModified = "2015-08-18 14:42:59",
                instructionsToString = instructions.entries.joinToString(
                    separator = "\n\n"
                ) { entry ->
                    "${entry.key} : ${entry.value}"
                },
                ingredientsToString = ingredients.joinToString()
            )
        ),
        onClickFavorite = {_,_ -> }
    )
}