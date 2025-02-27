package com.aritra.notify.components.topbar

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aritra.notify.R
import com.aritra.notify.components.ShareOption
import com.aritra.notify.data.models.Note
import com.aritra.notify.screens.notes.editNoteScreen.EditScreenViewModel
import eu.wewox.modalsheet.ExperimentalSheetApi
import eu.wewox.modalsheet.ModalSheet
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSheetApi::class)
@Composable
fun EditNoteTopBar(
    viewModel: EditScreenViewModel,
    noteId: Int,
    navigateBack: () -> Unit,
    title: String,
    description: String,
    dateTime: Date,
){
    var showSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        title = {
            Text(
                text = "",
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    painterResource(R.drawable.back),
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(onClick = { showSheet = true }) {
                Icon(
                    painterResource(R.drawable.share),
                    contentDescription = "share"
                )
            }
            ModalSheet(
                visible = showSheet,
                onVisibleChange = { showSheet = it},
                backgroundColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    ShareOption(
                        text = "Share note as text",
                        onClick = {
                            val sharingIntent = Intent(Intent.ACTION_SEND)
                            sharingIntent.type = "text/plain"

                            sharingIntent.putExtra(Intent.EXTRA_TEXT, "${"Title: $title"}\n${"Note: $description"}")
                            context.startActivity(Intent.createChooser(sharingIntent, "Share via"))
                            showSheet = false
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Share note as picture",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(onClick = { showSheet = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.outline
                        )
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.poppins_light))
                        )
                    }
                }
            }
            IconButton(onClick = {
                val currentDateTime = Date()
                val updateNote = Note(noteId,title,description,currentDateTime)
                viewModel.updateNotes(updateNote)
                navigateBack()
                Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painterResource(R.drawable.save),
                    contentDescription = "save"
                )
            }

        }
    )
}