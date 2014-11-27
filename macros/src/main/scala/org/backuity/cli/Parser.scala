package org.backuity.cli

class Parser {
  private var version : Option[String] = None
  private var help : Option[String] = Some("help")
  private var usage : Usage = Usage.Default
  private var args: List[String] = Nil

  def version(version: String) : Parser = {
    this.version = Some(version)
    this
  }

  def noHelp() : Parser = {
    this.help = None
    this
  }

  def withUsage(usage: Usage) : Parser = {
    this.usage = usage
    this
  }

  def parse(args: Array[String]) : Parser = {
    this.args = args.toList
    this
  }

  // terminal methods, i.e that execute the parser

  def withCommands[T <: Command : Manifest](withCommands : T*) : T = {
    val commands = Commands(withCommands : _*)
    // TODO
    withCommands(0)
  }

  def withCommand[T](command: Command)(onSuccess: => T): T = {
    command.read(args)
    onSuccess
  }
}