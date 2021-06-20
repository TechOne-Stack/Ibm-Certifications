<template>
  <div>
    <h1>{{ currentVoucher.state }}</h1>
    <p>{{ currentVoucher.validUntil | formatDate }}</p>
    <span>{{ currentVoucher.voucherCode }}</span>
    <!-- <span>{{ currentVoucher._links.certification.href }}</span> -->
    <br />
    <h1 class="font-bold">Available for:</h1>
    <div>
      {{ voucherDetailData }}
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import Vue from "vue";
import moment from "moment";
import axios from "axios";

Vue.filter("formatDate", function(value) {
  if (value) {
    return moment(String(value)).format("DD/MM/YYYY hh:mm");
  }
});

export default {
  name: "VoucherDetail",
  data() {
    return {
      currentVoucher: {},
    };
  },
  created() {
    this.currentVoucher = this.vouchers.find(
      ({ id }) => id == this.$route.params.id
    );
  },
  computed: {
    ...mapGetters(["vouchers"]),
    ...mapGetters(["voucherDetailData"]),
  },
  async mounted() {
    const voucherData = await axios.get(
      "http://localhost:8080/vouchers/" +
        this.currentVoucher.id +
        "/certification"
    );
    console.log("Voucher Data: ");
    console.log(voucherData.data);
    this.voucherDetailMutation(voucherData.data);
  },
  methods: {
    ...mapMutations(["voucherDetailMutation"]),
  },
};
</script>

<style></style>