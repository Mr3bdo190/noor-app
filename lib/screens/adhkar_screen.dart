import 'package:flutter/material.dart';

class AdhkarScreen extends StatefulWidget {
  const AdhkarScreen({super.key});

  @override
  State<AdhkarScreen> createState() => _AdhkarScreenState();
}

class _AdhkarScreenState extends State<AdhkarScreen> {
  // بيانات الأذكار
  final List<Map<String, dynamic>> _adhkar = [
    {'text': 'سُبْحَانَ اللَّهِ وَبِحَمْدِهِ', 'count': 33, 'total': 33},
    {'text': 'أَسْتَغْفِرُ اللَّهَ وَأَتُوبُ إِلَيْهِ', 'count': 100, 'total': 100},
    {'text': 'لا حَوْلَ وَلا قُوَّةَ إِلا بِاللَّهِ', 'count': 10, 'total': 10},
    {'text': 'اللَّهُمَّ صَلِّ وَسَلِّمْ عَلَى نَبِيِّنَا مُحَمَّدٍ', 'count': 10, 'total': 10},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.only(top: 60, left: 20, right: 20, bottom: 20),
      itemCount: _adhkar.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) {
          return const Padding(
            padding: EdgeInsets.only(bottom: 20),
            child: Text('الأذكار اليومية', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF38BDF8))),
          );
        }
        
        final zikr = _adhkar[index - 1];
        final isDone = zikr['count'] == 0;

        return Card(
          color: isDone ? Colors.green.withOpacity(0.15) : const Color(0xFF1E293B),
          margin: const EdgeInsets.only(bottom: 15),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(15),
            side: BorderSide(color: isDone ? Colors.green : const Color(0xFF334155)),
          ),
          child: InkWell(
            onTap: isDone ? null : () {
              setState(() {
                _adhkar[index - 1]['count']--;
              });
            },
            borderRadius: BorderRadius.circular(15),
            child: Padding(
              padding: const EdgeInsets.all(20),
              child: Column(
                children: [
                  Text(zikr['text'], style: const TextStyle(fontSize: 20, color: Colors.white, height: 1.5), textAlign: TextAlign.center),
                  const SizedBox(height: 20),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      isDone 
                        ? const Row(children: [Icon(Icons.check_circle, color: Colors.green), SizedBox(width: 5), Text('اكتمل', style: TextStyle(color: Colors.green, fontWeight: FontWeight.bold))])
                        : Text('المتبقي: ${zikr['count']}', style: const TextStyle(color: Color(0xFF38BDF8), fontSize: 16, fontWeight: FontWeight.bold)),
                      CircularProgressIndicator(
                        value: 1 - (zikr['count'] / zikr['total']),
                        backgroundColor: Colors.grey.shade800,
                        color: isDone ? Colors.green : const Color(0xFF38BDF8),
                      )
                    ],
                  )
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
