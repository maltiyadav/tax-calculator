package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class UsersController extends Controller {

  def index = Action { implicit request =>
    Ok("Application is running")
  }

}
