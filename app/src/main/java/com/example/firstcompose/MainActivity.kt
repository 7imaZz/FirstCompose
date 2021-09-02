package com.example.firstcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcompose.ui.theme.SampleData


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(SampleData.conversationSample)
        }
    }

    data class Message(
        val name: String,
        val body: String
    )

    @Composable
    fun MessageCard(message: Message){
        Row(
            modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = "Profile Pic",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(
                        1.5.dp,
                        MaterialTheme.colors.secondary,
                        CircleShape
                    )
                    .scale(1.5f, 1.5f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false)}
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primaryVariant
                else MaterialTheme.colors.surface
            )

            Column {
                Text(
                    text = message.name,
                    color = MaterialTheme.colors.secondary)
                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier.clickable {
                        isExpanded = !isExpanded
                    },
                    color = surfaceColor) {
                    Text(
                        text = message.body,
                        Modifier.padding(4.dp),
                        style = MaterialTheme.typography.subtitle2,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    )
                }
            }
        }
    }

    @Composable
    @Preview("Light Mode")
    @Preview(
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    fun PreviewMessageCard(){
        MessageCard(
            Message(
            "7ima",
            "Hello Everyone")
        )
    }

    @Composable
    fun Conversation(messages: List<Message>){
        LazyColumn {
            items(messages){message->
                MessageCard(message = message)
            }
        }
    }

    @Preview("Light Mode")
    @Preview(
        name = "Dark Mode",
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true
    )
    @Composable
    fun PreviewConversation(){
        Conversation(messages = SampleData.conversationSample)
    }
}