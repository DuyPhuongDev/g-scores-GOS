"use client";

import { Bar, BarChart, XAxis, YAxis } from "recharts";

import { Card, CardContent } from "@/components/ui/card";
import {
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
  type ChartConfig,
} from "@/components/ui/chart";
import { ChartDataResponse } from "@/api/type";

const chartConfig = {
  excellent: {
    label: "Excellent",
    color: "#FCA5A5",
  },
  good: {
    label: "Good",
    color: "#FDE68A",
  },
  average: {
    label: "Average",
    color: "#CBD5E1",
  },
  weak: {
    label: "Weak",
    color: "#1e3a8a",
  },
} satisfies ChartConfig;

export function StackBarChart({
  chartData,
}: {
  chartData: ChartDataResponse[];
}) {
  const data = chartData.map((item) => ({
    name: item.subjectName,
    excellent: item.excellent,
    good: item.good,
    average: item.average,
    weak: item.weak,
  }));
  return (
    <Card>
      <CardContent>
        <ChartContainer config={chartConfig}>
          <BarChart accessibilityLayer data={data}>
            <XAxis
              dataKey="name"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
              tick={{ fontSize: 10, fontWeight: 700, fill: "#94A3B8" }}
              tickFormatter={(value) => {
                return value;
              }}
            />
            <YAxis
              axisLine={false}
              tickLine={false}
              tick={{ fontSize: 10, fill: "#94a3b8" }}
              tickFormatter={(v) => `${v}`}
            />
            <Bar
              dataKey="weak"
              stackId="a"
              fill={chartConfig.weak.color}
              radius={[0, 0, 4, 4]}
            />
            <Bar
              dataKey="average"
              stackId="a"
              fill={chartConfig.average.color}
              radius={[0, 0, 4, 4]}
            />
            <Bar
              dataKey="good"
              stackId="a"
              fill={chartConfig.good.color}
              radius={[0, 0, 4, 4]}
            />
            <Bar
              dataKey="excellent"
              stackId="a"
              fill={chartConfig.excellent.color}
              radius={[4, 4, 0, 0]}
            />
            <ChartTooltip
              content={<ChartTooltipContent />}
              cursor={false}
              defaultIndex={1}
            />
          </BarChart>
        </ChartContainer>
      </CardContent>
    </Card>
  );
}
