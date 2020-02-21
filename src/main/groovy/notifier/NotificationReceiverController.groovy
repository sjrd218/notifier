/*
 * Copyright 2020 Rundeck, Inc. (http://rundeck.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package notifier

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

import javax.inject.Inject

@Controller("/notify")
class NotificationReceiverController {

    XmlMapper mapper = new XmlMapper()

    @Inject
    ApplicationEventPublisher eventPublisher;

    @Post(value="/",consumes = MediaType.ALL)
    HttpResponse<String> receiveNotification(@Body String payload) {
        Map notification = mapper.readValue(payload, Map)
        eventPublisher.publishEvent(new NotificationEvent(payload:notification))
        return HttpResponse.ok("ok")
    }
}
