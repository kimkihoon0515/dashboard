<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Name</th>
          <th class="text-left">Usage</th>
          <th class="text-left">MA predict</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in desserts" :key="item.name">
          <td>{{ item.name }}</td>
          <td>{{ item.useage }}</td>
          <td>{{ item.MApredict }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
  export default {
    name:"StorageTable",
    data () {
      return {
        desserts: [
          {
            name: 'Frozen Yogurt',
            useage: 159,
            MApredict: 0
          },
        ],
      }
    },
  }
</script>
