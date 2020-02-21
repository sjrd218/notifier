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

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.websocket.WebSocketBroadcaster
import io.micronaut.websocket.WebSocketSession
import io.micronaut.websocket.annotation.OnMessage
import io.micronaut.websocket.annotation.OnOpen
import io.micronaut.websocket.annotation.ServerWebSocket

import javax.inject.Inject

@ServerWebSocket("/ws")
class WebsocketController implements ApplicationEventListener<NotificationEvent> {

    @Inject
    WebSocketBroadcaster broadcaster

    @OnOpen
    void onOpen(WebSocketSession session) { }

    @Override
    void onApplicationEvent(final NotificationEvent event) {
        broadcaster.broadcastSync(event.payload)
    }

    @OnMessage
    void onMessage(String message, WebSocketSession session) {
        println "from client: " + message
        //broadcaster.broadcastSync("hearing you loud and clear")
    }
}
