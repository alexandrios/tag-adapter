package dev.amin.tagadapterexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.amin.tagadapter.Tag
import dev.amin.tagadapter.TagAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore

class MainActivity : AppCompatActivity(), TagAdapter.TagAdapterListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.apply {
            adapter = TagAdapter(tagList, this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private val tagList: MutableList<Tag>
        get() {
            return mutableListOf(
                Tag("lopta"),
                Tag("loptanje"),
                Tag("loptast"),
                Tag("loptati se")
                        /*

                Tag("Exercise"),
                Tag("Be Cool"),
                Tag("Floss"),
                Tag("Read the Sign"),
                Tag("Meditation"),
                Tag("Be Cool in an awesome way"),
                Tag("Go Crazy"),
                Tag("Drink Water"),
                Tag("Tag Team"),
                Tag("No Alcohol"),
                Tag("Code like Crazy"),
                Tag("Zombies?"),
                Tag("Zero Life"),
                Tag("Just Don't do it"),
                Tag("Drunk in Funeral"),
                Tag("Listen to Opeth"),
                Tag("Small"),
                Tag("Not so Small"),
                Tag("Java"),
                Tag("Did anyone said Zombies?"),
                Tag("Android"),
                Tag("Proud looser :D"),
                Tag("Tale of two taggies"),
                Tag("No Pain no Tag"),
                Tag("Code for Food"),
                Tag("Bar Blatta"),
                Tag("No Burgers!"),
                Tag("Play Guitar"),
                Tag("Clap the Article"),
                Tag("Walk"),
                Tag("Medium is Awesome"),
                Tag("Kotlin"),
                Tag("Dream"),
                Tag("Freedom"),
                Tag("Less Sugar"),
                Tag("The longer the Tag the longer the Cell"),
                Tag("Discipline"),
                Tag("No to Drugs :D"),
                Tag("Avengers"),
                Tag("Run for your Life"),
                Tag("Margarita"),
                Tag("Candies")

                         */
            )
        }

    var toast: Toast? = null

    @SuppressLint("ShowToast")
    override fun onTagClick(tag: Tag) {
        toast?.cancel()
        toast = Toast.makeText(this, tag.title, Toast.LENGTH_LONG)
        toast?.show()
    }

    var flag = true
    fun clickButton(view: View) {
        flag = !flag
        var t: MutableList<Tag> = if (flag)
            mutableListOf(
                Tag("lopta"),
                Tag("loptanje"),
                Tag("loptast"),
                Tag("loptati se") )
        else
            mutableListOf(
                Tag("lopta"),
                Tag("loptanje"),
                Tag("loptast"),
                Tag("loptati se"),
                Tag("polulopta"))

        rv.apply {
            adapter = TagAdapter(t, this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
