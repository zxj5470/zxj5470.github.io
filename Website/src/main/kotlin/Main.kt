import top.tented.support.dsl.css.css
import top.tented.support.dsl.html.*
import java.io.File

val someCss = File("../css/some.css")
val website = File("../index.html")

fun someCss() = css {
	id("red") {
		color = "red"
	}
}.apply {
	someCss.writeText(toString())
}

fun resources() {
	someCss()
}

fun website() = html {
	head {
		meta(charset = "UTF-8")
		title { + "Hello world!" }
		link(href = "css/some.css", type = "text/css", rel = "stylesheet")
		script(src = "js/lib/kotlin.js")
		script(src = "js/JavaScript_main.js")
	}

	body {
		h1 { + "这是一个标题" }
		p { + "这是一个段落" }
		p(id = "red") { + "这是一个红色的段落, 颜色由CSS提供" }
	}
}.apply {
	website.writeText(toString())
}


fun main(args : Array<String>) {
	resources()
	website()
}