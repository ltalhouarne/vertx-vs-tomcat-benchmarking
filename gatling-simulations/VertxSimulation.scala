/**   * Copyright 2011-2016 GatlingCorp (http://gatling.io)  *  * Licensed under the Apache License, Version 2.0 (the "License");  * you may not use this file except in compliance with the License.  * You may obtain a copy of the License at  *  *  http://www.apache.org/licenses/LICENSE-2.0  *  * Unless required by applicable law or agreed to in writing, software  * distributed under the License is distributed on an "AS ISBASIS,  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  * See the License for the specific language governing permissions and  * limitations under the License.  */

package computerdatabase

  import io.gatling.core.Predef._ 
import io.gatling.http.Predef._ 
import scala.concurrent.duration._

class VertxSimulation extends Simulation {
    
  var duration: Long = 15000
      var users: Int = 3000

       val httpConf = http  
        .baseURLs("http://localhost:8081")  
        .acceptHeader("Content-type: application/json;")  
        .acceptLanguageHeader("en-US,en;q=0.5")  
        .disableCaching

       val scn = scenario("Vertx benchmarking")  
        .during(Duration(duration, "millis")) {
          exec(http("Vertx messages")  
          .post("/vertx")  
          .body(StringBody("{\"transactions\":[{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello3\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello4\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello5\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello6\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello7\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello8\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello9\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello10\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-2\",\"message\":\"hello2\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello3\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello4\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello5\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello6\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello7\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello8\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello9\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello10\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-2\",\"message\":\"hello2\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello3\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello4\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello5\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello6\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello7\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello8\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello9\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-1\",\"message\":\"hello10\"},{\"id\":\"c9fd439d-3e78-4638-8faa-f40c78424e8b-2\",\"message\":\"hello2\"}]}")).asJSON)
     
        }      

    setUp (scn.inject(atOnceUsers(users)).protocols(httpConf))
   
}

 
