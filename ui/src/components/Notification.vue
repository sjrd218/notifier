<template>
    <div class="notification" v-bind:class="ncolor">
        <div class="hdr">{{message.project}} - {{message.job}}</div>
        <div>Status: {{message.status}}</div>
        <div>Started By: {{message.user}}</div>
        <div>Started: {{message.startDate}}</div>
        <div>Completed: {{message.endDate}}</div>
    </div>
</template>

<script>
  export default {
    name: "Notification",
    props: {
      message: Object
    },
    computed: {
      ncolor() {
        if(this.message.trigger === 'success') return "green"
        else if(this.message.trigger === 'failure') return "red"
        return ""
      }
    }
  }
</script>

<style scoped>
    .notification {
        text-align: left;
        padding: 10px;
        border-radius: 3px;
        background-color: #f2f2f2;
    }
    .green {
        background-color: #c4eec8 !important;
        border: 1px solid #42b983;

    }
    .red {
        background-color: #ffe5e6 !important;
        border: 1px solid #ff0000;
    }
    .hdr {
        font-weight: bold;
        border-bottom: 1px solid #999;
    }

</style>