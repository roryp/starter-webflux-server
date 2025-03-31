/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ai.mcp.sample.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class EchoService {

    /**
     * Echo back the input message
     * @param message The message to echo
     * @return The original message
     */
    @Tool(description = "Echo back the input message exactly as received")
    public String echoMessage(String message) {
        return message;
    }

    /**
     * Echo back a text message with a prefix
     * @param text The text to echo
     * @return String with prefix added
     */
    @Tool(description = "Echo back text with a prefix")
    public String echoWithPrefix(String text) {
        return "Echoed text: " + text;
    }
}