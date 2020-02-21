<template>
  <div id="app">
    <div class="hdr">Notifier</div>
    <div class="notifications">
      <hr/>
      <notification :message="msg" v-for="msg in messages" :key="msg.id"></notification>
    </div>
  </div>
</template>

<script>
import Notification from "./components/Notification";
var ws
export default {
  name: 'App',
  components: {
    Notification
  },
  data() {
    return {
      demoMessages: [
        {
          trigger:"success",
          status:"succeeded",
          project:"Project 1",
          job:"Job 1",
          executionId: "1",
          user: "admin",
          startDate: "2020-02-21T21:06:24Z",
          endDate: "2020-02-21T21:06:24Z"
        },
        {
          trigger:"failed",
          status:"failed",
          project:"Project 1",
          job:"Job 1",
          executionId: "2",
          user: "admin",
          startDate: "2020-02-21T21:16:24Z",
          endDate: "2020-02-21T21:16:24Z"
        }
      ],
      messages: [],
      wsDisconnected: true
    }
  },
  methods: {
    startReceiver() {
      console.log("starting receiver")
      ws = new WebSocket("ws://localhost:8085/ws")
      ws.onopen = () => {
        // eslint-disable-next-line no-console
        console.log("open conn")
        this.wsDisconnected = false
      }
      ws.onmessage = (msg) => {
        // eslint-disable-next-line no-console
        console.log(msg)
        const nmsg = JSON.parse(msg.data)
        this.messages.push({
          trigger:nmsg.trigger,
          status:nmsg.status,
          project:nmsg.executions.execution.project,
          job:nmsg.executions.execution.job.name,
          executionId: nmsg.executionId,
          user: nmsg.executions.execution.user,
          startDate: nmsg.executions.execution["date-started"][""],
          endDate: nmsg.executions.execution["date-ended"][""]
        })
        //this.infrastructure = JSON.parse(msg.data)
      }
      ws.onclose = () => {
        // eslint-disable-next-line no-console
        console.log("Closed")
        this.wsDisconnected = true
      }
    }
  },
  mounted() {
    this.startReceiver()
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.hdr {
  font-size: 1.2em;
  font-weight: bold;
}
  .notifications {
    margin: 10px auto;
    width: 400px;
  }
  .notifications > div {
    margin: 10px;
  }
</style>
