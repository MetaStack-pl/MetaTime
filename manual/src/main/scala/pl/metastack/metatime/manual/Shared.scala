package pl.metastack.metatime.manual

import java.io.File

import pl.metastack.metatime.BuildInfo

trait Shared {
  val organisation = "MetaStack-pl"
  val repoName = s"$organisation.github.io"
  val projectName = "metatime"
  val projectPath = new File("..", repoName)
  val manualPath = new File(projectPath, projectName)
  val manualPathStr = manualPath.getPath
  val manualVersionPath = new File(manualPath, "v" + BuildInfo.version)
  val manualVersionPathStr = manualVersionPath.getPath
  val imagesPath = new File(manualVersionPath, "images")
  val isSnapshot = BuildInfo.version.endsWith("SNAPSHOT")
}
